package com.app.carlos.ccg;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Sugestao extends AppCompatActivity {

    SwitchCompat switchCompat;
    Button enviarSugestao;
    RadioGroup radioGroup;
    EditText campoTexto,campoNome, campoTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestao);

        switchCompat = findViewById(R.id.switchAnomino);
        FloatingActionButton btnVoltar = findViewById(R.id.btnVoltar);
        enviarSugestao = findViewById(R.id.enviarSugestao);
        radioGroup = findViewById(R.id.radioGroup);
        campoNome = findViewById(R.id.campoNome);
        campoTelefone = findViewById(R.id.campoTelefone);
        campoTexto = findViewById(R.id.campoTexto);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sugestao.this, MainActivity.class));
            }
        });

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    campoNome.setEnabled(false);
                    campoTelefone.setEnabled(false);
                    campoNome.setHint("");
                    campoTelefone.setHint("");
                    Toast.makeText(Sugestao.this, "Você optou pelo Anonimato!", Toast.LENGTH_SHORT).show();
                } else {
                    campoNome.setEnabled(true);
                    campoTelefone.setEnabled(true);
                    campoNome.setHint("Nome");
                    campoTelefone.setHint("Telefone");
                }

            }
        });

        enviarSugestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sugestao.this, "Obrigado por nos contatar!", Toast.LENGTH_SHORT).show();

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.radioSugestao:
                        break;
                    case R.id.radioReclamacao:
                        break;
                    case R.id.radioDenuncia:
                        break;
                }
            }
        });

    }
}
