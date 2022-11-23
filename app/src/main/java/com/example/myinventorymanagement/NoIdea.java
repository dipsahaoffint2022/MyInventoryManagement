package com.example.myinventorymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class NoIdea extends AppCompatActivity {

    RecyclerView RecView;
    My_Adapter_2 adapter_2;

    Button set_btn;
    EditText nameidea,priceidea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_no_idea );

        RecView = findViewById ( R.id.RecView );
        set_btn = findViewById ( R.id.set_bttn );
        nameidea = findViewById ( R.id.nameidea );
        priceidea = findViewById ( R.id.priceidea );



        RecView.setLayoutManager ( new LinearLayoutManager ( NoIdea.this ) );

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model> ()
                        .setQuery ( FirebaseDatabase.getInstance ( Register.link ).getReference ().child ( "Users" ).child ( Login.userID ).child ( "Inventory" ), Model.class )
                        .build ();
//****************************************************************************
        adapter_2 = new My_Adapter_2 ( options, getApplicationContext () );//*
//   here getApplicationContext is very importent to make an Intent on My_Adapter_2.java to pass any value from java to any activity                                          ************************
//****************************************************************************
        RecView.setAdapter ( adapter_2 );
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter_2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter_2.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s) {
        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users").child(Login.userID).child("Inventory").orderByChild("a_p_name").startAt(s).endAt(s+"\uf8ff"), Model.class)
                        .build();
        adapter_2= new My_Adapter_2 (options,getApplicationContext ());
        adapter_2.startListening();
        RecView.setAdapter(adapter_2);
    }

}