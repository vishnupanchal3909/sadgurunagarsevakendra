package com.vishnu.sadgurunagarsevakendra.YuvaSenaSadgurunagar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

import vishnu.sadgurunagarsevakendra.R;


public class YuvaSenaAdapter  extends RecyclerView.Adapter<YuvaSenaAdapter.MyViewHolder> {

    Context context;
    ArrayList<YuvaSenaData> yuvaSenaDataArrayList;

    public YuvaSenaAdapter(Context context, ArrayList<YuvaSenaData> yuvaSenaDataArrayList) {
        this.context = context;
        this.yuvaSenaDataArrayList = yuvaSenaDataArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.yuvatimepass,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        YuvaSenaData yuvaSenaData=yuvaSenaDataArrayList.get(position);
        holder.name.setText(yuvaSenaData.getName());
        holder.mobile.setText(yuvaSenaData.getMobile());
        holder.jangnan.setText(yuvaSenaData.getJangnan());

    }

    @Override
    public int getItemCount() {
        return yuvaSenaDataArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,mobile,jangnan;
        public MyViewHolder( View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.yuvaansweryuva);
            mobile=itemView.findViewById(R.id.mobileyuva);
            jangnan=itemView.findViewById(R.id.jangananumberyuva);

        }
    }
}
