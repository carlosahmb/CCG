package com.app.carlos.ccg;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class AddLoja extends AppCompatActivity {

    RecyclerView recyclerLojistaId;
    int total_item_add = 0, last_visible_item_add;
    AddLojaHolder adapter_add;
    boolean isLoading_add = false, isMaxData_add = false;
    String last_key_add = "", id_user;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private FirebaseStorage mStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loja);

        FloatingActionButton addLoja = findViewById(R.id.addLoja);
        FloatingActionButton btnVoltar = findViewById(R.id.btnVoltar);
        recyclerLojistaId = findViewById(R.id.recyclerLojistaId);


        getLastKeyFromFirebase_add();

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerLojistaId.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerLojistaId.getContext(), layoutManager.getOrientation());
        recyclerLojistaId.addItemDecoration(dividerItemDecoration);

        adapter_add = new AddLojaHolder(this);
        recyclerLojistaId.setAdapter(adapter_add);

        mStorage = FirebaseStorage.getInstance();


        recyclerLojistaId.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                total_item_add = layoutManager.getItemCount();
                last_visible_item_add = layoutManager.findLastCompletelyVisibleItemPosition();

            }
        });

        addLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddLoja.this, CadastroLoja.class));
            }
        });

        /*btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddLoja.this, LoginActivity.class));
            }
        });*/

        getLojista();
    }

    private void getLojista() {
        iniciarFirebase();
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            id_user = firebaseUser.getUid();
        }
        if (!isMaxData_add) {
            final Query query;
            query = FirebaseDatabase.getInstance().getReference().child("Users").child(id_user);

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        List<Loja> newLojas2 = new ArrayList<>();
                        for (DataSnapshot lojaLojistaSnapShot : dataSnapshot.getChildren()) {

                            if (!lojaLojistaSnapShot.getKey().equals("email") &&
                                    !lojaLojistaSnapShot.getKey().equals("id") &&
                                    !lojaLojistaSnapShot.getKey().equals("username")) {
                                newLojas2.add(lojaLojistaSnapShot.getValue(Loja.class));
                            }


                        }

                      /*  last_node_add = newLojas.get(newLojas.size() - 1).getBox();

                        if (!last_node_add.equals(last_key_add))
                            newLojas.remove(newLojas.size() - 1);
                        else
                            last_node_add = "Erro nas listas";*/
                        adapter_add.addAll(newLojas2);
                        isLoading_add = false;
                    } else {
                        isLoading_add = false;
                        isMaxData_add = true;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    isLoading_add = false;
                }
            });
        }

    }

    private void getLastKeyFromFirebase_add() {
        Query getLastKey_add = FirebaseDatabase.getInstance().getReference().child("Users")
                .orderByKey().limitToLast(1);

        getLastKey_add.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot lastKey_add : dataSnapshot.getChildren())
                    last_key_add = lastKey_add.getKey();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AddLoja.this, "As Lojas não podem ser mostradas", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuLogout:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(AddLoja.this, MainActivity.class));
                break;
        }
        return true;
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(AddLoja.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


}

