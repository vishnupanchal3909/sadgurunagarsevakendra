package com.vishnu.sadgurunagarsevakendra.Dharmakshetra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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

public class dharmakshetra extends AppCompatActivity {

    Button add;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    DharmAdapter dharmAdapter;
    ArrayList<DharmUser> dharmlist;
    String m_text;
    final String vishnu="224339v";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dharmakshetra);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        add=findViewById(R.id.addbtn3);

        recyclerView=findViewById(R.id.recycleview);
        databaseReference= FirebaseDatabase.getInstance().getReference("Dharmkshetra");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dharmlist=new ArrayList<>();
        dharmAdapter=new DharmAdapter(this,dharmlist);
        recyclerView.setAdapter(dharmAdapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dharmlist.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    DharmUser userData=dataSnapshot.getValue(DharmUser.class);
                    dharmlist.add(userData);
                }
                dharmAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder my=new AlertDialog.Builder(dharmakshetra.this);
                my.setTitle("आपका जनगणना नंबर प्रविष्ट करे.");
                final EditText j=new EditText(dharmakshetra.this);
                j.setInputType(InputType.TYPE_CLASS_TEXT);
                my.setView(j);
                my.setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        m_text = j.getText().toString();
                        if(!m_text.equals(vishnu))
                        {
                            AlertDialog.Builder display=new AlertDialog.Builder(dharmakshetra.this);
                            display.setTitle("Error");
                            display.setMessage("इन्फॉर्मेशन ऍड करण्यासाठी सेवाकेंद्रातील धर्मक्षेत्र प्रमुख यांना संपर्क करा..श्री गुरुदेव");
                            display.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterfacedisplay, int i) {
                                    dialogInterfacedisplay.cancel();
                                }
                            });
                            display.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                            display.show();
                           // Toast.makeText(dharmakshetra.this, "Ya Janganan Number varil vykati hi sadguru nagar sevakendratil kontya padd var nahi .ShreeGurudev" + m_text, Toast.LENGTH_SHORT).show();
                        }
                        if(m_text.equals(vishnu))
                        {
                            Intent intent=new Intent(getApplicationContext(), AddDharmkshetra.class);
                            startActivity(intent);
                        }
                    }
                });
                my.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        dialogInterface.cancel();
                    }
                });
                my.create().show();
            }
        });
    }
}