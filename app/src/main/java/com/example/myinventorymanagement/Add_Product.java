package com.example.myinventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class Add_Product extends AppCompatActivity {
    public EditText Add_Product_Name, Add_Product_Price;
    public Button Add_Product_Button;

    public static String get_product;

    public static FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Add_Product_Name = findViewById(R.id.Add_Product_Name);
        Add_Product_Price = findViewById(R.id.Add_Product_Price);
        Add_Product_Button = findViewById(R.id.Add_Product_Button);

        String get_search = Add_Product_Name.getText().toString();

        database = FirebaseDatabase.getInstance(Register.link);

        Add_Product_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Add_Product_Name.getText().toString().equals("") && !Add_Product_Price.getText().toString().equals("")) {
                    get_product = Add_Product_Name.getText().toString();
                    String get_product_price = Add_Product_Price.getText().toString();

                    ProductData getdata = new ProductData(get_product, get_product_price);

                    database.getReference().child("Users").child(Login.userID).child("Inventory").child(get_product).setValue(getdata);
                    Toast.makeText(Add_Product.this, "Product Added Successfully", Toast.LENGTH_SHORT).show();
                }

                else
                    Toast.makeText(Add_Product.this, "Enter a valid string", Toast.LENGTH_SHORT).show();

            }
        });

    }
}