package com.vishnu.sadgurunagarsevakendra.BhudanSanklpa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vishnu.sadgurunagarsevakendra.R;

public class BhudanAdapter extends RecyclerView.Adapter<BhudanAdapter.MyViewHolder> {

    Context context;
    ArrayList<BhudanData> bhudanArrayList;

    public BhudanAdapter(Context context, ArrayList<BhudanData> bhudanArrayList) {
        this.context = context;
        this.bhudanArrayList = bhudanArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.bhudantimepass,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            BhudanData bhudanData=bhudanArrayList.get(position);
            holder.name.setText(bhudanData.getName());
            holder.mobile.setText(bhudanData.getMobile());

    }

    @Override
    public int getItemCount() {
        return bhudanArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
            TextView name,mobile;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.BhudanSanklpa_name);
            mobile=itemView.findViewById(R.id.mobile_Bhudansanklpa);
        }
    }
}
