package com.example.myinventorymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public Button Inventory,generate,sell,noidea;
    public TextView name, sub_total,p_unit;
    public Spinner spinner;
    public EditText p_qty,price;

        ArrayList<String> pr_name = new ArrayList<> ();
        ArrayList<String> pr_price = new ArrayList<> ();
        ArrayList<String> pr_qty = new ArrayList<> ();
        ArrayList<String> pr_total = new ArrayList<> ();


    //spinner array_list making below
    ArrayList<String> arr_unit= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main_page );


        name = findViewById(R.id.p_name);
        price = findViewById ( R.id.p_price );
        p_qty = findViewById ( R.id.p_qty );
        p_unit = findViewById ( R.id.p_unit );
        sub_total = findViewById ( R.id.sub_total );
        sell = findViewById ( R.id.sell );
        Inventory = findViewById ( R.id.Inventory );
        generate = findViewById ( R.id.generate );
        noidea = findViewById ( R.id.noidea );
        spinner = findViewById ( R.id.spinner );


        name.setText ( getIntent ().getStringExtra ( "name" ) );
        price.setText ( getIntent ().getStringExtra ( "price"  ));



        //        spinner code

        arr_unit.add ( "kg" );
        arr_unit.add ( "g" );
        arr_unit.add ( "no." );

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<> ( this, android.R.layout.simple_spinner_dropdown_item,arr_unit );
        spinner.setAdapter ( spinnerAdapter );
        spinner.setOnItemSelectedListener ( this );
//******then implements two methods view on the line number 181******


        //******a button generate which basically generated the price base on the qty*****
        generate.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                if(p_unit.getText().toString().equals("g") && !p_qty.getText ().toString ().equals ("") && !price.getText ().toString ().equals ( "" ) && !name.getText ().toString ().equals ( "" )){
                    float p= (1000/(Float.parseFloat (p_qty.getText ().toString ())));
                    float generate_price = Float.parseFloat (price.getText().toString())/p;
                    String g_p = String.valueOf ( generate_price );
                    price.setText ( g_p );
                }
                else if(p_unit.getText().toString().equals("kg") && !p_qty.getText ().toString ().equals ("") && !price.getText ().toString ().equals ( "" ) && !name.getText ().toString ().equals ( "" )){
                    float generate_price = Float.parseFloat ( p_qty.getText ().toString () )*Float.parseFloat ( price.getText ().toString () );
                    String g_p = String.valueOf ( generate_price );
                    price.setText ( g_p );
                }
                else if(p_unit.getText().toString().equals("no.") && !p_qty.getText ().toString ().equals ("") && !price.getText ().toString ().equals ( "" ) && !name.getText ().toString ().equals ( "" )){
                    float generate_price = Float.parseFloat ( p_qty.getText ().toString () )*Float.parseFloat ( price.getText ().toString () );
                    String g_p = String.valueOf ( generate_price );
                    price.setText ( g_p );
                } else{
                    Toast.makeText ( MainPage.this, "enter a valid unit or qty", Toast.LENGTH_SHORT ).show ();
                }


            }
        } );




        Inventory.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent next = new Intent ( MainPage.this, Inventory.class );
                startActivity ( next );
            }
        } );

        noidea.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent next = new Intent (MainPage.this,NoIdea.class);
                startActivity ( next );
            }
        } );



        sell.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
//                ********************
                String pro_name = name.getText ().toString ();
                String pro_price= getIntent ().getStringExtra ( "price" );
                String qty = p_qty.getText ().toString ()+p_unit.getText ().toString () ;
                String tot = price.getText ().toString ();


                Toast.makeText ( MainPage.this, "product added to the list", Toast.LENGTH_SHORT ).show ();

//        defining table below
//        ArrayList<String> pr_name = new ArrayList<> ();
//        ArrayList<String> pr_price = new ArrayList<> ();
//        ArrayList<String> pr_qty = new ArrayList<> ();
//        ArrayList<String> pr_total = new ArrayList<> ();

                pr_name.add ( pro_name );
                pr_price.add (pro_price);
                pr_qty.add (qty );
                pr_total.add (tot);

                TableLayout table = (TableLayout) findViewById ( R.id.tb1 );

                TableRow row = new TableRow ( MainPage.this );



                TextView t1 = new TextView ( MainPage.this );
                TextView t2 = new TextView ( MainPage.this );
                TextView t3 = new TextView ( MainPage.this );
                TextView t4 = new TextView ( MainPage.this );



//                String total;
//
//                int sum=0;


                for (int i=0; i<pr_name.size();i++){
                    String pname = pr_name.get ( i );
                    String pprice = pr_price.get ( i );
                    String pqty = pr_qty.get ( i );
                    String ptot = pr_total.get ( i );

                    t1.setText ( pname );
                    t2.setText ( pprice );
                    t3.setText ( pqty );
                    t4.setText ( ptot );


//                    String os= String.valueOf (sum = sum + Integer.parseInt ( pr_total.get ( i ).toString ()));
//
//                    Toast.makeText ( MainPage.this, "Sell Clicked", Toast.LENGTH_SHORT ).show ();



                }

                row.addView ( t1 );
                row.addView ( t2 );
                row.addView ( t3 );
                row.addView ( t4 );

                table.addView ( row );
//
//                sub_total.setText (String.valueOf ( sum ));
                name.setText ( "" );
                price.setText ( "" );
                p_qty.setText ( "" );
//
                name.requestFocus ();
//               **********************
            }


        } );


    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition ( i ).toString ();
        p_unit.setText ( text );
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}