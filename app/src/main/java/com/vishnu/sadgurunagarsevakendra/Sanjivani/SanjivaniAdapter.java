package com.vishnu.sadgurunagarsevakendra.Sanjivani;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

import vishnu.sadgurunagarsevakendra.R;

public class SanjivaniAdapter extends RecyclerView.Adapter<SanjivaniAdapter.MyViewHolder> {

    Context context;
    ArrayList<SanjivaniData> sanjivaniDataArrayList;

    public SanjivaniAdapter(Context context, ArrayList<SanjivaniData> sanjivaniDataArrayList) {
        this.context = context;
        this.sanjivaniDataArrayList = sanjivaniDataArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.sanjivanitimepass,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SanjivaniData sanjivaniData=sanjivaniDataArrayList.get(position);
        holder.name.setText(sanjivaniData.getName());
        holder.mobile.setText(sanjivaniData.getMobile());
        holder.amount.setText(sanjivaniData.getAmount());

    }

    @Override
    public int getItemCount() {
        return sanjivaniDataArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
            TextView name,mobile,amount;
        public MyViewHolder( View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.sanjivavni_name_data);
            mobile=itemView.findViewById(R.id.mobile_sanjivani);
            amount=itemView.findViewById(R.id.amount_sanjivani);
        }
    }
}
