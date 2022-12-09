package com.vishnu.sadgurunagarsevakendra.Report;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

import vishnu.sadgurunagarsevakendra.R;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder> {

    Context context;
    ArrayList<ReportData> arrayList;

    public ReportAdapter(Context context, ArrayList<ReportData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(context).inflate(R.layout.reporttimepass,parent,false);
            return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        ReportData user=arrayList.get(position);
        holder.month.setText(user.getMonth());
        holder.years.setText(user.getYears());
        holder.reportt.setText(user.getReport());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView month,years,reportt;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            month=itemView.findViewById(R.id.monthreportreport);
            years=itemView.findViewById(R.id.yearreportreport);
            reportt=itemView.findViewById(R.id.reportreport);
        }
    }
}
