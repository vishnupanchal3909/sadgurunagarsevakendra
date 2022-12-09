package com.vishnu.sadgurunagarsevakendra.Timepass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import vishnu.sadgurunagarsevakendra.R;

public class MyAdapter extends RecyclerView.Adapter< MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<UserData> userData;

    public MyAdapter(Context context, ArrayList<UserData> userData) {
        this.context = context;
        this.userData = userData;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.timepass,parent,false);
        return  new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        UserData user=userData.get(position);
        holder.first.setText(user.getName());
        holder.Mobile.setText(user.getMobile());
        holder.jangnan.setText(user.getJangnan());
        holder.gender.setText(user.getSex());

    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView first,Mobile,jangnan,gender;

        public MyViewHolder(@NonNull  View itemView) {

            super(itemView);
            first=itemView.findViewById(R.id.answername);
            Mobile=itemView.findViewById(R.id.mobile);
            jangnan=itemView.findViewById(R.id.jangananumber);
            gender=itemView.findViewById(R.id.gender);
        }
    }
}
