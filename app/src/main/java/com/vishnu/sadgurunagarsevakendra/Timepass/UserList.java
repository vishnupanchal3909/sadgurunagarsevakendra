package com.vishnu.sadgurunagarsevakendra.Timepass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vishnu.sadgurunagarsevakendra.R;

public class UserList extends AppCompatActivity {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<UserData> list;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        add=findViewById(R.id.addbtn4);
        recyclerView=findViewById(R.id.recycleview);
        databaseReference= FirebaseDatabase.getInstance().getReference("Student");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        myAdapter=new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                list.clear();
               for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                   UserData userData=dataSnapshot.getValue(UserData.class);
                   list.add(userData);
               }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), UserList.class);
                startActivity(intent);
            }
        });
    }
}