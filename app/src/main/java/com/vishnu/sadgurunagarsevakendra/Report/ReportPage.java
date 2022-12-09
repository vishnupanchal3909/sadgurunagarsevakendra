package com.vishnu.sadgurunagarsevakendra.Report;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vishnu.sadgurunagarsevakendra.R;

public class ReportPage extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ReportAdapter reportAdapter;
    ArrayList<ReportData> reportDataArrayList;
    Button add;
    String m_text;
    final String vishnu="224339v";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        add=findViewById(R.id.button3);
        recyclerView=findViewById(R.id.recyclerView9);
        reference= FirebaseDatabase.getInstance().getReference("Report");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        reportDataArrayList=new ArrayList<>();
        reportAdapter=new ReportAdapter(this,reportDataArrayList);
        recyclerView.setAdapter(reportAdapter);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reportDataArrayList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ReportData user=dataSnapshot.getValue(ReportData.class);
                    reportDataArrayList.add(user);
                }
                reportAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ReportPage.this);
                builder.setTitle("आपका जनगणना नंबर प्रविष्ट करे. ");
                final EditText text=new EditText(ReportPage.this);
                builder.setView(text);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        m_text=text.getText().toString();
                        if(!m_text.equals(vishnu))
                        {
                                AlertDialog.Builder alert=new AlertDialog.Builder(ReportPage.this);
                                alert.setTitle("Error");
                                alert.setMessage("आपल्या महिन्याच्या अहवाल हे सेवाकेंद्रतील संजीवनी प्रमुख ऍड करतात. श्री गुरूदेव..!");
                                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                                alert.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                                alert.show();
                        }
                        if(m_text.equals(vishnu))
                        {
                            Intent intent=new Intent(getApplicationContext(),AddReport.class);
                            startActivity(intent);
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });
    }
}