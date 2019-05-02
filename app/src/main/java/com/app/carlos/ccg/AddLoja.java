package com.app.carlos.ccg;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddLoja extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loja);

        FloatingActionButton addLoja = findViewById(R.id.addLoja);
        FloatingActionButton btnVoltar = findViewById(R.id.btnVoltar);

        addLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddLoja.this, CadastroLoja.class));
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddLoja.this, MainActivity.class));
            }
        });
    }


}
