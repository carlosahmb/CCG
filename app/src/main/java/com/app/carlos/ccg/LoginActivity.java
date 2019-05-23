package com.app.carlos.ccg;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    ProgressBar progressBarLoad;
    TextView txtResetSenha;
    static String txtEmail = "";
    static String txtSenhaLogin = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        progressBarLoad = findViewById(R.id.progressBarLoad);
        email = findViewById(R.id.email);
        senhalogin = findViewById(R.id.senhalogin);
        btnlogin = findViewById(R.id.btnlogin);
        btnCriar = findViewById(R.id.btnCriar);
        FloatingActionButton btnVoltar = findViewById(R.id.btnVoltar);
        txtResetSenha = findViewById(R.id.txtResetSenha);
        

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEmail = email.getText().toString();
                txtSenhaLogin = senhalogin.getText().toString();

                if (TextUtils.isEmpty(txtEmail)){
                    email.setError("Informe seu e-mail!");
                    email.requestFocus();
                    Toast.makeText(LoginActivity.this, "Todos os campos devem estar preenchidos.", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(txtSenhaLogin)){
                    senhalogin.setError("Informe sua senha!");
                    senhalogin.requestFocus();
                    Toast.makeText(LoginActivity.this, "Todos os campos devem estar preenchidos.", Toast.LENGTH_SHORT).show();
                }

                else {
                    progressBarLoad.setVisibility(View.VISIBLE);

                    auth.signInWithEmailAndPassword(txtEmail, txtSenhaLogin)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBarLoad.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(LoginActivity.this, AddLoja.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(LoginActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG);
                                        Toast.makeText(LoginActivity.this, "O e-mail ou a senha estão incorretos.", Toast.LENGTH_SHORT).show();
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

        txtResetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (LoginActivity.this, ResetSenhaActivity.class));
            }
        });

        }
}
