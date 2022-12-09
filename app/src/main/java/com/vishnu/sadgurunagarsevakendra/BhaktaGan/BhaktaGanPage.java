package com.vishnu.sadgurunagarsevakendra.BhaktaGan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vishnu.sadgurunagarsevakendra.R;

public class BhaktaGanPage extends AppCompatActivity {

    Button add,attendance;
    RecyclerView recyclerView;
    BhaktAdapter bhaktAdapter;
    ArrayList<BhaktData> bhaktData;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhakta_gan_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        add=findViewById(R.id.addbtn5);
        attendance=(Button) findViewById(R.id.upstiti);
        recyclerView=findViewById(R.id.recycleview5);
        reference = FirebaseDatabase.getInstance().getReference("BhaktGan");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        bhaktData = new ArrayList<>();
        bhaktAdapter = new BhaktAdapter(getApplicationContext(), bhaktData);
        recyclerView.setAdapter(bhaktAdapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                bhaktData.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    BhaktData user = dataSnapshot.getValue(BhaktData.class);
                    bhaktData.add(user);
                }
                bhaktAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        attendance.setVisibility(View.INVISIBLE);
        /////////////////////////////////////////////////////////////
        Date current= Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        Log.d("Time",simpleDateFormat.format(current));
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEE");
        Log.d("Time2",simpleDateFormat2.format(current));
        if(Integer.parseInt( simpleDateFormat.format(current))>=9 &&
                simpleDateFormat2.format(current).compareTo("Sun")==0 &&
                Integer.parseInt(simpleDateFormat.format(current))<=10)
        {
            String fprmatdate= DateFormat.getInstance().format(current);
            attendance.setVisibility(View.VISIBLE);
        }
        /////////////////////////////////////////////////////////////
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddBhaktGan.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddUpstiti.class));
            }
        });
    }
}