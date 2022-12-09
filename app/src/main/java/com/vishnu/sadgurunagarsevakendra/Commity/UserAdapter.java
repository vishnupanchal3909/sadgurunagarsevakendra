package com.vishnu.sadgurunagarsevakendra.Commity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vishnu.sadgurunagarsevakendra.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> userArrayList;


    public UserAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v1= LayoutInflater.from(context).inflate(R.layout.commitytimepass,parent,false);
        return new MyViewHolder(v1);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        User user=userArrayList.get(position);
        holder.name.setText(user.getName());
        holder.mobile.setText(user.getMobile());
        holder.pad.setText(user.getSit());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,mobile,pad;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.commityname);
            mobile=itemView.findViewById(R.id.commitymobile);
            pad=itemView.findViewById(R.id.commitypadname);

        }
    }
}
