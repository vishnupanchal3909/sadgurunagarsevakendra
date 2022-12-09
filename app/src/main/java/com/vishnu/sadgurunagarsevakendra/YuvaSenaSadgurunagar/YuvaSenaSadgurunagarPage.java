package com.vishnu.sadgurunagarsevakendra.YuvaSenaSadgurunagar;

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

public class YuvaSenaSadgurunagarPage extends AppCompatActivity {

    Button add;
    RecyclerView yuvasenarecycleview;
    YuvaSenaAdapter yuvaSenaAdapter;
    ArrayList<YuvaSenaData>  yuvaSenaData;
    DatabaseReference reference;
    String m_text;
    final String vishnu="224339v";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuva_sena_sadgurunagar_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        add=findViewById(R.id.yuvaaddbutton);
        yuvasenarecycleview=findViewById(R.id.yuvarecycleview);
        reference = FirebaseDatabase.getInstance().getReference("YuvaSena");
        yuvasenarecycleview.setHasFixedSize(true);
        yuvasenarecycleview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        yuvaSenaData = new ArrayList<>();
        yuvaSenaAdapter = new YuvaSenaAdapter(getApplicationContext(), yuvaSenaData);
        yuvasenarecycleview.setAdapter(yuvaSenaAdapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                yuvaSenaData.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    YuvaSenaData user=dataSnapshot.getValue(YuvaSenaData.class);
                    yuvaSenaData.add(user);
                }
                yuvaSenaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder my=new AlertDialog.Builder(YuvaSenaSadgurunagarPage.this);
                my.setTitle("आपका जनगणना नंबर प्रविष्ट करे.");
                final EditText j=new EditText(YuvaSenaSadgurunagarPage.this);
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
                            AlertDialog.Builder display=new AlertDialog.Builder(YuvaSenaSadgurunagarPage.this);
                            display.setTitle("Error");
                            display.setMessage("इन्फॉर्मेशन ऍड करण्यासाठी सेवाकेंद्रातील युवा अध्यक्ष यांना संपर्क करा..श्री गुरुदेव ");
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
                            //Toast.makeText(YuvaSenaSadgurunagarPage.this, "Ya Janganan Number varil vykati hi sadguru nagar sevakendratil kontya padd var nahi .ShreeGurudev" + m_text, Toast.LENGTH_SHORT).show();
                        }
                        if(m_text.equals(vishnu))
                        {
                            Intent intent=new Intent(getApplicationContext(), AddYuvaSenaSadguruNagar.class);
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