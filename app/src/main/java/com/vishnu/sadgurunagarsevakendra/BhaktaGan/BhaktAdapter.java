package com.vishnu.sadgurunagarsevakendra.BhaktaGan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vishnu.sadgurunagarsevakendra.R;

public class BhaktAdapter extends RecyclerView.Adapter<BhaktAdapter.MyViewHolder> {

    Context context;
    ArrayList<BhaktData> bhaktDataArrayList;

    public BhaktAdapter(Context context, ArrayList<BhaktData> bhaktDataArrayList) {
        this.context = context;
        this.bhaktDataArrayList = bhaktDataArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(context).inflate(R.layout.timepass,parent,false);
            return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BhaktData bhaktData=bhaktDataArrayList.get(position);
        holder.name.setText(bhaktData.getName());
        holder.mobile.setText(bhaktData.getMobile());
        holder.jnumber.setText(bhaktData.getJnumber());
        holder.sex.setText(bhaktData.getSex());


    }

    @Override
    public int getItemCount() {
        return bhaktDataArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,mobile,sex,jnumber,home;
        public MyViewHolder( View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_bhakta);
            mobile=itemView.findViewById(R.id.mobile_bhakt);
            sex=itemView.findViewById(R.id.gender);
            jnumber=itemView.findViewById(R.id.jangnan_bhakta);

        }
    }
}
