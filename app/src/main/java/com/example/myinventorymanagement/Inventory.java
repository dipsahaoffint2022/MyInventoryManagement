package com.example.myinventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.orhanobut.dialogplus.OnBackPressListener;

public class Inventory extends AppCompatActivity {
    public Button Add_Button, View_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        Add_Button = findViewById(R.id.Add_Button);
        View_Button = findViewById(R.id.View_Button);

        Add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(Inventory.this,Add_Product.class);
                startActivity(next);
            }
        });

        View_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(Inventory.this,View_Product.class);
                startActivity(next);
            }
        });

//        Intent next = new Intent (Inventory.this,MainPage.class);
//        onBackPressed ();

    }
}