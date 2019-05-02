package com.app.carlos.ccg;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class CadastroLoja extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    MaterialEditText inserirLoja, inserirBox, inserirTelWhats, inserirTelFixo, inserirReferencia, inserirInstagram;
    Button btnInserirCadastro;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_loja);

        Spinner spinner1 = (Spinner) findViewById(R.id.inserirCategoria1);
        Spinner spinner2 = (Spinner) findViewById(R.id.inserirCategoria2);

        inserirBox = findViewById(R.id.inserirBox);
        inserirInstagram = findViewById(R.id.inserirInstagram);
        inserirLoja = findViewById(R.id.inserirLoja);
        inserirReferencia = findViewById(R.id.inserirReferencia);
        inserirTelWhats = findViewById(R.id.inserirTelWhats);
        inserirTelFixo = findViewById(R.id.inserirTelFixo);
        btnInserirCadastro = findViewById(R.id.btnInserirCadastro);
        FloatingActionButton btnVoltar = findViewById(R.id.btnVoltar);

        reference = FirebaseDatabase.getInstance().getReference();

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastroLoja.this, AddLoja.class));
            }
        });

        btnInserirCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtInserirLoja = inserirLoja.getText().toString();
                String txtInserirBox = inserirBox.getText().toString();
                String txtInserirTelWhats = inserirTelWhats.getText().toString();
                String txtInserirTelFixo = inserirTelFixo.getText().toString();
                String txtInserirReferencia = inserirReferencia.getText().toString();
                String txtInserirInstagram = inserirInstagram.getText().toString();

                if(inserirLoja.length() ==0){
                    inserirLoja.setError("Digite o nome da Loja!");

                } else if(inserirBox.length() ==0){
                    inserirBox.setError("Digite o número do Box!");

                }else if(inserirTelWhats.length() ==0 || inserirTelWhats.length() >=10){
                    inserirTelWhats.setError("Digite um telefone válido! Ex: 123456789");

                } else if(inserirTelFixo.length() ==0 || inserirTelFixo.length() >=9){
                    inserirTelFixo.setError("Digite um telefone fixo válido! Ex: 12345678");

                }else {
                    if(txtInserirLoja.length() >0){
                        reference.push().setValue(txtInserirLoja);
                    }else{
                        inserirLoja.setError("Digite o nome da Loja!");
                    }
                    if(txtInserirBox.length() >0){
                        reference.push().setValue(txtInserirBox);
                    }else{
                        inserirBox.setError("Digite o número do Box!");
                    }
                    if(txtInserirTelWhats.length() ==9){
                        reference.push().setValue(txtInserirTelWhats);
                    }else{
                        inserirTelWhats.setError("Digite um telefone válido! Ex: 123456789");
                    }
                    if(txtInserirTelFixo.length() ==8){
                        reference.push().setValue(txtInserirTelFixo);
                    }else{
                        inserirTelFixo.setError("Digite um telefone fixo válido! Ex: 12345678");
                    }
                    if(txtInserirReferencia.length()==6){
                        reference.push().setValue(txtInserirReferencia);
                    }else{
                        inserirReferencia.setError("Referência muita curta!");
                    }
                    if(txtInserirInstagram.length() >=2){
                        reference.push().setValue(txtInserirInstagram);
                    }else{
                        inserirInstagram.setError("Instagram não é válido");
                    }

                    inserirBox.setText("");
                    inserirInstagram.setText("");
                    inserirLoja.setText("");
                    inserirReferencia.setText("");
                    inserirTelWhats.setText("");
                    inserirTelFixo.setText("");

                    Toast.makeText(CadastroLoja.this, "Loja Cadastrada com sucesso!", Toast.LENGTH_LONG).show();

                }


            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lista_categoria_produto, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void registrar(final String txtInserirLoja, String txtInserirBox, String txtInserirTelWhats,
                           String txtInserirTelFixo, String txtInserirReferencia, String txtInserirInstagram){

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String text = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
