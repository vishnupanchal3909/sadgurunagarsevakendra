package com.vishnu.sadgurunagarsevakendra.Report;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vishnu.sadgurunagarsevakendra.R;

public class AddReport extends AppCompatActivity {

    AutoCompleteTextView month,year;
    EditText report;
    Button submit;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        month=findViewById(R.id.monthreport);
        year=findViewById(R.id.yearreport);
        submit=findViewById(R.id.submitreport);
        report=findViewById(R.id.report);
        reference= FirebaseDatabase.getInstance().getReference("Report");

        String []month2=new String[]{
                "Jan","Feb","March","April","May","june","july","August","Sept","October","November","December"
        };
        ArrayAdapter<String> list=new ArrayAdapter<>(
                AddReport.this,R.layout.dropdownmenu,month2
        );
        month.setAdapter(list);

        String[]year2=new String[]{
                "2020","2021","2022","2023","2024","2025",
        };
        ArrayAdapter<String> list2=new ArrayAdapter<>(
                AddReport.this,R.layout.dropdownmenu,year2
        );
        year.setAdapter(list2);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MONTH,YEAR,REPORT;
                MONTH=month.getText().toString();
                YEAR=year.getText().toString();
                REPORT=report.getText().toString();

                if(TextUtils.isEmpty(MONTH)){
                    month.setError("Please,Select The Month");
                    return;
                }
                if(TextUtils.isEmpty(YEAR)){
                    year.setError("Please,Select The Year");
                    return;
                }
                if(TextUtils.isEmpty(REPORT)){
                    report.setError("Enter Details In Report");
                    return;
                }
                ReportUserClassHelper reportUserClassHelper=new ReportUserClassHelper(MONTH,YEAR,REPORT);
                reference.push().setValue(reportUserClassHelper);
                Toast.makeText(AddReport.this, "Report Added SuccessFully...!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}