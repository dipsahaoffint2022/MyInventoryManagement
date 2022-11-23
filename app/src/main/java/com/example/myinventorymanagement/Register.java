package com.example.myinventorymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    public EditText Register_Name_Enter, Register_Mail_Enter, Register_Code_Enter, Register_Phone_Enter;
    public Button Register_Button;

    FirebaseAuth auth;
    FirebaseDatabase database;

    public static String link = "https://my-inventory-management-default-rtdb.firebaseio.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Register_Name_Enter = findViewById(R.id.Register_Name_Enter);
        Register_Mail_Enter = findViewById(R.id.Register_Mail_Enter);
        Register_Code_Enter = findViewById(R.id.Register_Code_Enter);
        Register_Phone_Enter = findViewById(R.id.Register_Phone_Enter);
        Register_Button = findViewById(R.id.Login_Button);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance(link);

        Register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String get_email = Register_Mail_Enter.getText().toString();
                String get_password = Register_Code_Enter.getText().toString();
                UserData ob = new UserData(Register_Name_Enter.getText().toString(), get_email, get_password, Register_Phone_Enter.getText().toString());
                Toast.makeText(Register.this, "test1", Toast.LENGTH_SHORT).show();
                auth.createUserWithEmailAndPassword(get_email,get_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Register.this, "test", Toast.LENGTH_SHORT).show();
                        if(task.isSuccessful()) {
                            Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            String userid = auth.getUid();

                            database.getReference().child("Users").child(userid).setValue(ob);

                        }
                        else
                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }
}