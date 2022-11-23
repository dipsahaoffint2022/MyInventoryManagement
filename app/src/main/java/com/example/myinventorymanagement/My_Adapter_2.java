package com.example.myinventorymanagement;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class My_Adapter_2 extends FirebaseRecyclerAdapter<Model,My_Adapter_2.MyViewHolder_2> {
    //   below context is very importent to pass any value from here to any activity
    Context context;
    public My_Adapter_2(@NonNull FirebaseRecyclerOptions<Model> options, Context context) {
//                                              *********it is very important to context above**********
        super ( options );
//******it is also important to initialize the context below******
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder_2 holder, @SuppressLint("RecyclerView") final int position, @NonNull Model model) {

        holder.m_p_name.setText(model.getA_p_name ());
        holder.m_p_price.setText ( model.getA_p_price () );
        holder.set.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {


//                MainPage ob = new MainPage ();
//
//                ob.name.setText ( model.getA_p_name ());
//                ob.price.setText ( model.getA_p_price () );

                Intent next = new Intent (context,MainPage.class);
                next.putExtra ("name",model.getA_p_name ());
                next.putExtra ("price",model.getA_p_price ());
                next.setFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
                context.startActivity (next);
                Toast.makeText ( context, "by default price set in 1 kg", Toast.LENGTH_SHORT ).show ();


            }
        } );
    }

    @NonNull
    @Override
    public MyViewHolder_2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.second_singlerow,parent,false );
        return  new MyViewHolder_2 ( view );
    }

    class MyViewHolder_2 extends RecyclerView.ViewHolder{
        TextView m_p_name, m_p_price;
        Button set;

        public MyViewHolder_2(@NonNull View itemView) {
            super ( itemView );

            m_p_name = itemView.findViewById ( R.id.m_p_name );
            m_p_price = itemView.findViewById ( R.id.m_p_price );
            set = itemView.findViewById ( R.id.set );


        }
    }

}
