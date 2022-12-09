package com.vishnu.sadgurunagarsevakendra.JivanRkshak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import vishnu.sadgurunagarsevakendra.R;

public class JivanAdater extends RecyclerView.Adapter<JivanAdater.MyViewHolder> {

    Context context;
    ArrayList<JivanData> dataArrayList;

    public JivanAdater(Context context, ArrayList<JivanData> dataArrayList) {
        this.context = context;
        this.dataArrayList = dataArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.jivantimepass,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        JivanData jivanData=dataArrayList.get(position);
        holder.name.setText(jivanData.getName());
        holder.mobile.setText(jivanData.getMobile());

    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,mobile;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.jivan_name_data);
            mobile=itemView.findViewById(R.id.mobile_jivan);
        }
    }
}
