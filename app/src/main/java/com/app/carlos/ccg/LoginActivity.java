package com.app.carlos.ccg;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {

    MaterialEditText email, senhalogin;
    Button btnlogin, btnCriar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        senhalogin = findViewById(R.id.senhalogin);
        btnlogin = findViewById(R.id.btnlogin);
        btnCriar = findViewById(R.id.btnCriar);
        FloatingActionButton btnVoltar = findViewById(R.id.btnVoltar);
        
        

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtEmail = email.getText().toString();
                String txtSenhaLogin = senhalogin.getText().toString();

                if (TextUtils.isEmpty(txtEmail)){
                    email.setError("Informe seu e-mail!");
                    Toast.makeText(LoginActivity.this, "Todos os campos devem estar preenchidos.", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(txtSenhaLogin)){
                    senhalogin.setError("Informe sua senha!");
                    Toast.makeText(LoginActivity.this, "Todos os campos devem estar preenchidos.", Toast.LENGTH_SHORT).show();
                }

                else {
                    auth.signInWithEmailAndPassword(txtEmail, txtSenhaLogin)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(LoginActivity.this, AddLoja.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(LoginActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(LoginActivity.this, "Autenticação falhou.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }

        });

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CriarContaActivity.class));
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (LoginActivity.this, MainActivity.class));
            }
        });

        }

}
