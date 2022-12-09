package com.vishnu.sadgurunagarsevakendra.NandDatak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import vishnu.sadgurunagarsevakendra.R;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    ArrayList<NandData> nandDataArrayList;

    public Adapter(Context context, ArrayList<NandData> nandDataArrayList) {
        this.context = context;
        this.nandDataArrayList = nandDataArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.nandtimepass,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NandData data=nandDataArrayList.get(position);
        holder.name.setText(data.getName());
        holder.mobile.setText(data.getMobile());
    }

    @Override
    public int getItemCount() {
        return nandDataArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,mobile;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_nand_dattak);
            mobile=itemView.findViewById(R.id.mobile_nand_dattak);
        }
    }
}
