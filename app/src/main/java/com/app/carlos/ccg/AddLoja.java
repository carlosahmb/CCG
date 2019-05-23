package com.app.carlos.ccg;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AddLoja extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    List<Loja> lojas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loja);
        /*firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Lojas");*/

       /* Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

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

       /* reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lojas = new ArrayList<Loja>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                    Loja value = dataSnapshot1.getValue(Loja.class);
                    Loja viewLoja = new Loja();
                    String nomeLoja = value.getNomeLoja();
                    String box = value.getBox();
                    String telWhats = value.getTelefoneWhats();
                    String telFixo = value.getTelefoneFixo();
                    String refPonto = value.getPontoReferencia();
                    String instagram = value.getInstagram();
                    viewLoja.setNomeLoja(nomeLoja);
                    viewLoja.setBox(box);
                    viewLoja.setTelefoneWhats(telWhats);
                    viewLoja.setTelefoneFixo(telFixo);
                    viewLoja.setPontoReferencia(refPonto);
                    viewLoja.setInstagram(instagram);
                    lojas.add(viewLoja);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Hello", "Failed to read value.", databaseError.toException());

            }
        });

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(lojas,this);
        RecyclerView.LayoutManager recycle = new GridLayoutManager(this,1);
        recycle.setLayoutManager(recycle);
        recycle.setItemAnimator( new DefaultItemAnimator());
        recycle.setAdapter(recyclerAdapter);
        */
    }



}
