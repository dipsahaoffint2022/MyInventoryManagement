package com.example.myinventorymanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainPage_Second extends AppCompatActivity {

        ArrayList<String> pr_name = new ArrayList<> ();
        ArrayList<String> pr_price = new ArrayList<> ();
        ArrayList<String> pr_qty = new ArrayList<> ();
        ArrayList<String> pr_total = new ArrayList<> ();

//        TextView name,price,qty,unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_second);

//        name = findViewById ( R.id.p_name);
//        price = findViewById ( R.id.p_price );
//        qty = findViewById ( R.id.p_qty );
//        unit = findViewById ( R.id.p_unit );

        String name1 = getIntent ().getStringExtra ( "name" );
        String price1 = getIntent ().getStringExtra ( "price" );
        String qty1 = getIntent ().getStringExtra ( "qty" );
        String total1 = getIntent ().getStringExtra ( "total" );

                pr_name.add ( name1 );
                pr_price.add (price1);
                pr_qty.add (qty1 );
                pr_total.add (total1);

                TableLayout table = (TableLayout) findViewById ( R.id.tb1 );

                TableRow row = new TableRow ( MainPage_Second.this );



                TextView t1 = new TextView ( MainPage_Second.this );
                TextView t2 = new TextView ( MainPage_Second.this );
                TextView t3 = new TextView ( MainPage_Second.this );
                TextView t4 = new TextView ( MainPage_Second.this );





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
//                    Toast.makeText ( MainPage_Second.this, "Sell Clicked", Toast.LENGTH_SHORT ).show ();



                }

                row.addView ( t1 );
                row.addView ( t2 );
                row.addView ( t3 );
                row.addView ( t4 );

                table.addView ( row );


//                name.setText ( "" );
//                price.setText ( "" );
//                qty.setText ( "" );
//                name.requestFocus ();

//
//                sub_total.setText (String.valueOf ( sum ));


    }
}