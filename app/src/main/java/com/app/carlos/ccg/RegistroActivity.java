package com.app.carlos.ccg;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class RegistroActivity extends AppCompatActivity {

    MaterialEditText username, email, senhalogin;
    Button btnRegistrar;
    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        senhalogin = findViewById(R.id.senhalogin);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        auth = FirebaseAuth.getInstance();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtUsername = username.getText().toString();
                String txtEmail= email.getText().toString();
                String txtSenhaLogin = senhalogin.getText().toString();

                if(TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtSenhaLogin)){
                    Toast.makeText(RegistroActivity.this, "Todos os campos devem estar preenchidos.", Toast.LENGTH_SHORT).show();
                } else if (txtSenhaLogin.length() < 6){
                    Toast.makeText(RegistroActivity.this, "Senha muito curta, precisa mais que 6 caracteres", Toast.LENGTH_LONG).show();
                } else{
                    registrar(txtUsername, txtEmail, txtSenhaLogin);
                }

            }
        });
    }
    private void registrar (final String username,String email, String senhalogin){

        auth.createUserWithEmailAndPassword(email, senhalogin)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child((userid));

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username", username);
                            hashMap.put("imageURL", "default");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        } else{
                            Toast.makeText(RegistroActivity.this, "Você não pode se registrar com este e-mail ou senha", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}
