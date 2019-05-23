package com.app.carlos.ccg;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.util.HashMap;

public class CriarContaActivity extends AppCompatActivity {

    MaterialEditText username, email;
    EditText senhalogin;
    Button btnRegistrar;
    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressBar progressBarLoad;
    static String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);


        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        senhalogin = findViewById(R.id.senhalogin);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        FloatingActionButton btnVoltar = findViewById(R.id.btnVoltar);
        progressBarLoad = findViewById(R.id.progressBarLoad);

        auth = FirebaseAuth.getInstance();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtUsername = username.getText().toString().trim();
                String txtEmail= email.getText().toString().trim();
                String txtSenhaLogin = senhalogin.getText().toString().trim();

                if(TextUtils.isEmpty(txtUsername)){
                    username.setError("Informe seu nome!");
                    username.requestFocus();
                    Toast.makeText(CriarContaActivity.this, "Todos os campos devem estar preenchidos.", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(txtEmail) || (!validarCampoEmail(txtEmail))){
                    email.setError("Informe um e-mail válido!");
                    email.requestFocus();
                    Toast.makeText(CriarContaActivity.this, "Todos os campos devem estar preenchidos.", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(txtSenhaLogin)){
                    senhalogin.setError("Informe uma senha!");
                    senhalogin.requestFocus();
                    Toast.makeText(CriarContaActivity.this, "Todos os campos devem estar preenchidos.", Toast.LENGTH_SHORT).show();
                } else if (txtSenhaLogin.length() < 6){
                    Toast.makeText(CriarContaActivity.this, "Senha muito curta, precisa 6 caracteres ou mais", Toast.LENGTH_LONG).show();
                } else{
                    progressBarLoad.setVisibility(View.VISIBLE);
                    registrar(txtUsername, txtEmail, txtSenhaLogin);
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CriarContaActivity.this, LoginActivity.class));
            }
        });
    }
    private void registrar (final String username, final String email, String senhalogin){

        auth.createUserWithEmailAndPassword(email, senhalogin)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child((userid));

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username", username);
                            hashMap.put("email", email);

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBarLoad.setVisibility(View.GONE);
                                    if (task.isSuccessful()){
                                        Toast.makeText(CriarContaActivity.this, "Conta registrada com sucesso!", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(CriarContaActivity.this, LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            });
                        } else{
                            if(task.getException()instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(CriarContaActivity.this, "Esta conta já está registrada.", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    private boolean validarCampoEmail(String validaEmail){
        boolean resultado = (Patterns.EMAIL_ADDRESS.matcher(validaEmail).matches());
        return resultado;
    }
}
