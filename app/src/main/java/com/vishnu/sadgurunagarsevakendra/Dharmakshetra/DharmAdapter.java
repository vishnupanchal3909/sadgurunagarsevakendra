package com.vishnu.sadgurunagarsevakendra.Dharmakshetra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import vishnu.sadgurunagarsevakendra.R;

public class DharmAdapter extends RecyclerView.Adapter<DharmAdapter.MyViewHolder> {

    Context context;
    ArrayList<DharmUser> dharmUserArrayList;

    public DharmAdapter(Context context, ArrayList<DharmUser> dharmUserArrayList) {
        this.context = context;
        this.dharmUserArrayList = dharmUserArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.dharmtimepass,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DharmUser dharmUser=dharmUserArrayList.get(position);
        holder.first.setText(dharmUser.getName());
        holder.mobile.setText(dharmUser.getMobile());
        holder.jangnan.setText(dharmUser.getJangnan());
        holder.startdate.setText(dharmUser.getStartdate());
        holder.enddate.setText(dharmUser.getEnddate());

    }

    @Override
    public int getItemCount() {
        return dharmUserArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView first,mobile,jangnan,startdate,enddate;;

        public MyViewHolder( View itemView) {

            super(itemView);
            first=itemView.findViewById(R.id.answername);
            mobile=itemView.findViewById(R.id.mobile);
            jangnan=itemView.findViewById(R.id.jangananumber);
            startdate=itemView.findViewById(R.id.startdate);
            enddate=itemView.findViewById(R.id.enddate);
        }
    }
}
