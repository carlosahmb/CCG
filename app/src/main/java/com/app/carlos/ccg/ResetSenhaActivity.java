package com.app.carlos.ccg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class ResetSenhaActivity extends AppCompatActivity {

    MaterialEditText editEmail;
    Button btnRedefinirSenha;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_senha);


        editEmail = findViewById(R.id.editEmail);
        btnRedefinirSenha = findViewById(R.id.btnRedefinirSenha);
        FloatingActionButton btnVoltar = findViewById(R.id.btnVoltar);


        btnRedefinirSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtEmail = editEmail.getText().toString().trim();
                resetSenha(txtEmail);
            }
        });


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (ResetSenhaActivity.this, MainActivity.class));
            }
        });

        }

    private void resetSenha(String txtEmail) {
        auth.sendPasswordResetEmail(txtEmail).addOnCompleteListener(ResetSenhaActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ResetSenhaActivity.this, "Um e-mail para redefinir sua senha foi enviado! Siga os passos.",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ResetSenhaActivity.this, LoginActivity.class));
                } else{
                    Toast.makeText(ResetSenhaActivity.this, "E-mail não registrado, verifique se o e-mail está correto.",Toast.LENGTH_LONG).show();
                    editEmail.setText("");
                    editEmail.setError("E-mail não registrado!");
                }
            }
        });
    }
    protected void onStart(){
        super.onStart();
        auth = FirebaseAuth.getInstance();
    }

}
