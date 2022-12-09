package com.vishnu.sadgurunagarsevakendra.Commity;

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


public class CommityPage extends AppCompatActivity
{

    Button addbtn;
    DatabaseReference reference;
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    ArrayList<User> arrayList;
    String m_text;
    final String vishnu="224339v";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commity_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        addbtn=findViewById(R.id.addbtn);
        recyclerView=findViewById(R.id.recycleview2);
        reference= FirebaseDatabase.getInstance().getReference("Student");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        arrayList=new ArrayList<>();
        userAdapter=new UserAdapter(this,arrayList);
        recyclerView.setAdapter(userAdapter);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    User user=dataSnapshot.getValue(User.class);
                    arrayList.add(user);
                }
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder my=new AlertDialog.Builder(CommityPage.this);
                my.setTitle("आपका जनगणना नंबर प्रविष्ट करे.");
                final EditText j=new EditText(CommityPage.this);
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
                            AlertDialog.Builder display=new AlertDialog.Builder(CommityPage.this);
                            display.setTitle("Error");
                            display.setMessage(" सद्गुरू नगर सेवाकेंद्र मध्ये कमिटी ऍड करण्यासाठी सेवा केंद्र अध्यक्ष,महिला अध्यक्ष यांना संपर्क करा. श्री गुरुदेव.!");
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
                           //Toast.makeText(CommityPage.this, "Ya Janganan Number varil vykati hi sadguru nagar sevakendratil kontya padd var nahi .ShreeGurudev" + m_text, Toast.LENGTH_SHORT).show();
                       }
                           if(m_text.equals(vishnu))
                        {
                            Intent intent=new Intent(getApplicationContext(), AddMemberToTheCommity.class);
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
//                if(m_text==vishnu){
//                    Intent intent=new Intent(getApplicationContext(), AddMemberToTheCommity.class);
//                    startActivity(intent);
//            }
            }

        });

    }
}
