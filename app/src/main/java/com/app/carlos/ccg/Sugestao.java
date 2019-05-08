package com.app.carlos.ccg;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import dmax.dialog.SpotsDialog;


public class Sugestao extends AppCompatActivity {

    String emailccg , senhaccg, nomeToast;
    String assuntoEmail = "*Sem assunto*";
    Session session = null;
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
        enviarSugestao.setEnabled(false);
        radioGroup = findViewById(R.id.radioGroup);
        campoNome = findViewById(R.id.campoNome);
        campoTelefone = findViewById(R.id.campoTelefone);
        campoTelefone.addTextChangedListener(MaskEditUtil.mask(campoTelefone, "(##)#####-####"));
        campoTexto = findViewById(R.id.campoTexto);
        final AlertDialog dialog = new SpotsDialog(this, R.style.dialogSpot);
        emailccg = "camelodromocg@gmail.com";
        senhaccg = "camelodromocg2019";

        campoNome.addTextChangedListener(enviarTextWatcher);
        campoTelefone.addTextChangedListener(enviarTextWatcher);
        campoTexto.addTextChangedListener(enviarTextWatcher);


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
                    campoNome.setText("Anônimo");
                    campoTelefone.setText("(00)00000-0000");
                    Toast.makeText(Sugestao.this, "Você optou pelo Anonimato!", Toast.LENGTH_SHORT).show();
                } else {
                    campoNome.setEnabled(true);
                    campoTelefone.setEnabled(true);
                    campoNome.setText("");
                    campoNome.setHint("Nome");
                    campoTelefone.setText("");
                    campoTelefone.setHint("Telefone");
                }

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.radioSugestao:
                        assuntoEmail = "*Sugestão*";
                        break;
                    case R.id.radioReclamacao:
                        assuntoEmail = "*Reclamação*";
                        break;
                    case R.id.radioDenuncia:
                        assuntoEmail = "*Denúncia*";
                        break;
                }
            }
        });



        enviarSugestao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Properties properties = new Properties();
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.socketFactory.port", "465");
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.port", "465");

                session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(emailccg, senhaccg);
                        }

                    });

                dialog.show();
                RetreiveFeedTask task = new RetreiveFeedTask();
                task.execute();

            }
            class RetreiveFeedTask extends AsyncTask<String, Void, String>{

                String txtCampoTexto = campoTexto.getText().toString();
                String txtCampoNome = campoNome.getText().toString();
                String txtCampoTelefone = campoTelefone.getText().toString();

                @Override
                protected String doInBackground(String... strings) {

                    try{
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(emailccg));
                        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("camelodromocg@gmail.com"));
                        message.setSubject("Suporte CCG: " + assuntoEmail + " " + txtCampoNome + " " + txtCampoTelefone);
                        message.setContent(txtCampoTexto, "text/html; charset=utf-8");
                        Transport.send(message);

                    }catch (MessagingException e ){
                        e.printStackTrace();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    return null;
                }

                protected void onPostExecute(String result){
                    dialog.cancel();
                    campoNome.setText("");
                    campoTelefone.setText("");
                    campoTexto.setText("");
                    if(txtCampoNome != "Anônimo"){
                        nomeToast = txtCampoNome;

                    }
                    Toast.makeText(Sugestao.this, "Obrigado por nos contatar " + nomeToast +"!", Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    private TextWatcher enviarTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String nomeCampoInput = campoNome.getText().toString().trim();
            String telefoneCampoInput = campoTelefone.getText().toString().trim();
            String textoCampoInput = campoTexto.getText().toString().trim();

            if(!nomeCampoInput.isEmpty() & !telefoneCampoInput.isEmpty() & !textoCampoInput.isEmpty()) {

                enviarSugestao.setText("");
                enviarSugestao.setEnabled(true);
                enviarSugestao.setBackgroundResource(R.drawable.botao_personalizado_pri);

            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            String nomeCampoInput = campoNome.getText().toString().trim();
            String telefoneCampoInput = campoTelefone.getText().toString().trim();
            String textoCampoInput = campoTexto.getText().toString().trim();

            if(nomeCampoInput.isEmpty() || telefoneCampoInput.isEmpty() || textoCampoInput.isEmpty()) {

                enviarSugestao.setText("Preencha os campos!");
                enviarSugestao.setEnabled(false);
                enviarSugestao.setBackgroundResource(R.drawable.botao_personalizado_default);

            }

        }
    };
}
