package com.example.forecastapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.JsonHttpResponseHandler;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    List<String> myList;
    EditText edt;
    JsonHttpResponseHandler ctx;
    public RecyclerAdapter(List<String> list , EditText edtCity , JsonHttpResponseHandler ctx){
        this.myList=list;
        this.edt=edtCity;
        this.ctx = ctx;

    }


    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycleritem, parent, false);
        RecyclerHolder holder = new RecyclerHolder(v,ctx,myList);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, int position) {
        String name = myList.get(position);
        holder.txtList.setText(name);
        holder.txtList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(holder.txtList.getText());
            }
        });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder{
        TextView txtList;
        EditText edtCity;

        public RecyclerHolder(@NonNull View itemView, JsonHttpResponseHandler ctx, List<String> myList) {
            super(itemView);

            edtCity = itemView.findViewById(R.id.edtCity);
            txtList = itemView.findViewById(R.id.txtList);
        }
    }
}
