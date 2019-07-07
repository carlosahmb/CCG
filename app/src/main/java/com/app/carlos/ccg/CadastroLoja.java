package com.app.carlos.ccg;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;


public class CadastroLoja extends AppCompatActivity {

    MaterialEditText inserirNomeLoja, inserirBox, inserirTelWhats, inserirTelFixo, inserirPontoReferencia, inserirInstagram;
    Button btnInserirCadastro;
    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;
    MaterialBetterSpinner spinner1, spinner2;
    static String id_loja;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_loja);


        final MaterialBetterSpinner spinner1 = findViewById(R.id.inserirCategoria1);
        final MaterialBetterSpinner spinner2 = findViewById(R.id.inserirCategoria2);


        inserirBox = findViewById(R.id.inserirBox);
        inserirInstagram = findViewById(R.id.inserirInstagram);
        inserirNomeLoja = findViewById(R.id.inserirNomeLoja);
        inserirPontoReferencia = findViewById(R.id.inserirPontoReferencia);
        inserirTelWhats = findViewById(R.id.inserirTelWhats);
        inserirTelFixo = findViewById(R.id.inserirTelFixo);
        btnInserirCadastro = findViewById(R.id.btnInserirCadastro);
        FloatingActionButton btnVoltar = findViewById(R.id.btnVoltar);
        inserirTelWhats.addTextChangedListener(MaskEditUtil.mask(inserirTelWhats, "(##)#####-####"));
        inserirTelFixo.addTextChangedListener(MaskEditUtil.mask(inserirTelFixo, "(##)####-####"));

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastroLoja.this, AddLoja.class));
            }
        });

        /*inserirBox.setText("23");
        inserirInstagram.setText("camelodromoccg");
        inserirNomeLoja.setText("Lojas M");
        inserirPontoReferencia.setText("Próximo a entrada");
        inserirTelWhats.setText("(67)98119-2508");
        inserirTelFixo.setText("(67)3443-0000");
        */


        btnInserirCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoriaSelecionada1 = spinner1.getText().toString().trim();
                String categoriaSelecionada2 = spinner2.getText().toString().trim();
                String txtInserirNomeLoja = inserirNomeLoja.getText().toString().trim();
                String txtInserirBox = inserirBox.getText().toString().trim();
                String txtInserirTelWhats = inserirTelWhats.getText().toString().trim();
                String txtInserirTelFixo = inserirTelFixo.getText().toString().trim();
                String txtInserirPontoReferencia = inserirPontoReferencia.getText().toString().trim();
                String txtInserirInstagram = inserirInstagram.getText().toString().trim();

                Loja loja = new Loja();

                loja.setNomeLoja(txtInserirNomeLoja);
                loja.setBox(txtInserirBox);
                loja.setTelefoneWhats(txtInserirTelWhats);
                loja.setTelefoneFixo(txtInserirTelFixo);
                loja.setPontoReferencia(txtInserirPontoReferencia);
                loja.setInstagram(txtInserirInstagram);
                loja.setCategoriaLoja1(categoriaSelecionada1);
                loja.setCategoriaLoja2(categoriaSelecionada2);


                if (inserirNomeLoja.length() <= 1) {
                    inserirNomeLoja.setError("Digite o nome da Loja!");
                    inserirNomeLoja.requestFocus();

                } else if (inserirBox.length() == 0) {
                    inserirBox.setError("Digite o número do Box!");
                    inserirBox.requestFocus();

                } else if (inserirTelWhats.length() <= 13) {
                    inserirTelWhats.setError("Digite um telefoneWhats válido! Ex: 123456789");
                    inserirTelWhats.requestFocus();

                } else if (spinner1.length() == 0 && spinner2.length() == 0) {
                    spinner1.setError("Defina pelo menos uma Categoria!");
                    spinner1.requestFocus();
                } else {
                    iniciarFirebase();
                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (firebaseUser != null) {
                        id_loja = firebaseUser.getUid();
                    }

                    //reference.child("Users").child(id_loja).child("Lojas").push().setValue(loja);
                    reference.child("Users").child(id_loja).child(loja.getBox()).setValue(loja);

                    inserirNomeLoja.setText("");
                    inserirBox.setText("");
                    inserirTelWhats.setText("");
                    inserirTelFixo.setText("");
                    inserirPontoReferencia.setText("");
                    inserirInstagram.setText("");
                    spinner1.setText("Selecione a 1ª categoria da loja");
                    spinner2.setText("Selecione a 2ª categoria da loja");
                    inserirNomeLoja.requestFocus();

                    Toast.makeText(CadastroLoja.this, "Loja " + txtInserirNomeLoja + " cadastrada com sucesso!", Toast.LENGTH_LONG).show();

                }
            }

        });

        ArrayList<String> dadosSpinner = new ArrayList<>();
        dadosSpinner.add("Automotivos");
        dadosSpinner.add("Assitência Técnica");
        dadosSpinner.add("Bolsas, Malas e Mochilas");
        dadosSpinner.add("Bonés e Acessórios");
        dadosSpinner.add("Brinquedos em Geral");
        dadosSpinner.add("Caça e Pesca");
        dadosSpinner.add("Celulares e Acessórios");
        dadosSpinner.add("Eletrônicos e Acessórios");
        dadosSpinner.add("Games e Acessórios");
        dadosSpinner.add("Informática e Acessórios");
        dadosSpinner.add("Jóias e Acessórios");
        dadosSpinner.add("Óculos e Armações");
        dadosSpinner.add("Perfumes e Cosméticos");
        dadosSpinner.add("Relógios e Acessórios");
        dadosSpinner.add("Roupas e Acessórios");
        dadosSpinner.add("Tabacaria");
        dadosSpinner.add("Outros");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, dadosSpinner);

        spinner1.setAdapter(spinnerArrayAdapter);
        spinner1.setOnItemSelectedListener(ouvinteSpinner);
        spinner2.setAdapter(spinnerArrayAdapter);
        spinner2.setOnItemSelectedListener(ouvinteSpinner);

    }


    AdapterView.OnItemSelectedListener ouvinteSpinner = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String itemSelecionadoSpinner1 = spinner1.getOnItemSelectedListener().toString();
            String itemSelecionadoSpinner2 = spinner2.getOnItemSelectedListener().toString();
            Toast.makeText(getBaseContext(), itemSelecionadoSpinner1, Toast.LENGTH_SHORT).show();
            Toast.makeText(getBaseContext(), itemSelecionadoSpinner2, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
   /* private int getIndex_SpinnerItem(Spinner spinner, String item){
        int index = 0;
        for(int i = 0; i < spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).equals(item)){
                index = i;
                break;
            }
        }
        return index;
    }*/

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(CadastroLoja.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
    }
}