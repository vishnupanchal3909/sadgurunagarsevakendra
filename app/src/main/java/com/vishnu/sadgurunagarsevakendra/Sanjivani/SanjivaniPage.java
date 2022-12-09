package com.vishnu.sadgurunagarsevakendra.Sanjivani;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.vishnu.sadgurunagarsevakendra.BhudanSanklpa.AddMemberToBhudan;
import com.vishnu.sadgurunagarsevakendra.BhudanSanklpa.BhudanAdapter;
import com.vishnu.sadgurunagarsevakendra.BhudanSanklpa.BhudanData;
import com.vishnu.sadgurunagarsevakendra.JivanRkshak.AddMemberToTheJivanRkshak;
import com.vishnu.sadgurunagarsevakendra.JivanRkshak.JivanAdater;
import com.vishnu.sadgurunagarsevakendra.JivanRkshak.JivanData;
import com.vishnu.sadgurunagarsevakendra.NandDatak.Adapter;
import com.vishnu.sadgurunagarsevakendra.NandDatak.NandData;
import com.vishnu.sadgurunagarsevakendra.NandDatak.Nanddatak;

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

public class SanjivaniPage extends AppCompatActivity {

    Spinner spinner;

    Button add,donarDetails;
    DatabaseReference sanjivanireference;
    RecyclerView recyclerView;
    String m_text;
    final String vishnu="224339v";


    //////////////////////////////////////////
    SanjivaniAdapter sanjivaniAdapter;
    ArrayList<SanjivaniData> arrayList;
    /////////////////////////////////////////
    JivanAdater jivanAdater;
    ArrayList<JivanData> jivanData;
    ////////////////////////////////////////
    BhudanAdapter bhudanAdapter;
    ArrayList<BhudanData> bhudanDataArrayList;
    ///////////////////////////////////////

    Adapter adapter;
    ArrayList<NandData> nandDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanjivani_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        add=findViewById(R.id.button5);
        spinner=findViewById(R.id.spinner);
        donarDetails=findViewById(R.id.button6);
        donarDetails.setVisibility(View.INVISIBLE);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String[] yognan=new String[]{
                "Select-->"," संजिवनी","जीवन रक्षक","भूदान संकल्प पूर्ती","नंद दत्तक पालक योजना",
        };
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,yognan);

        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if(adapterView.getItemAtPosition(i)==" संजिवनी"){

                        Toast.makeText(SanjivaniPage.this, ""+adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                        recyclerView=findViewById(R.id.sanjivanirecyclerView);
                        sanjivanireference = FirebaseDatabase.getInstance().getReference("Sanjivani");
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        donarDetails.setVisibility(View.VISIBLE);
                        arrayList = new ArrayList<>();
                        sanjivaniAdapter = new SanjivaniAdapter(getApplicationContext(), arrayList);
                        recyclerView.setAdapter(sanjivaniAdapter);
                        sanjivanireference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                arrayList.clear();
                                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                                {
                                    SanjivaniData user = dataSnapshot.getValue(SanjivaniData.class);
                                    arrayList.add(user);
                                }
                                sanjivaniAdapter.notifyDataSetChanged();

                            }
                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder my=new AlertDialog.Builder(SanjivaniPage.this);
                                my.setTitle("आपका जनगणना नंबर प्रविष्ट करे.");
                                final EditText j=new EditText(SanjivaniPage.this);
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
                                            AlertDialog.Builder display=new AlertDialog.Builder(SanjivaniPage.this);
                                            display.setTitle("Error");
                                            display.setMessage("इन्फॉर्मेशन ऍड करण्यासाठी सेवाकेंद्रातील संजिवनी प्रमुख यांना संपर्क करा..श्री गुरुदेव ");
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
                                            //Toast.makeText(SanjivaniPage.this, "Ya Janganan Number varil vykati hi sadguru nagar sevakendratil kontya padd var nahi .ShreeGurudev" + m_text, Toast.LENGTH_SHORT).show();
                                        }
                                        if(m_text.equals(vishnu))
                                        {
                                            Intent intent=new Intent(getApplicationContext(), AddSanjivani.class);
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
                        donarDetails.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                gotoUrl("https://docs.google.com/spreadsheets/d/14uobtLMji4fu4pXhdf3xHAXRWfTjNeaotQjTX9hgimQ/edit?usp=sharing");
                            }
                        });
                    }
                    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if(adapterView.getItemAtPosition(i)=="जीवन रक्षक"){

                        Toast.makeText(SanjivaniPage.this, ""+adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                        recyclerView=findViewById(R.id.sanjivanirecyclerView);
                        sanjivanireference = FirebaseDatabase.getInstance().getReference("JivanRkshak");
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        donarDetails.setVisibility(View.INVISIBLE);
                        jivanData = new ArrayList<>();
                        jivanAdater = new JivanAdater(getApplicationContext(), jivanData);
                        recyclerView.setAdapter(jivanAdater);
                        sanjivanireference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                jivanData.clear();
                                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                                {
                                    JivanData use1r = dataSnapshot.getValue(JivanData.class);
                                    jivanData.add(use1r);
                                }
                                jivanAdater.notifyDataSetChanged();

                            }
                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) { AlertDialog.Builder my=new AlertDialog.Builder(SanjivaniPage.this);
                                my.setTitle("आपका जनगणना नंबर प्रविष्ट करे.");
                                final EditText j=new EditText(SanjivaniPage.this);
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
                                            AlertDialog.Builder display=new AlertDialog.Builder(SanjivaniPage.this);
                                            display.setTitle("Error");
                                            display.setMessage("इन्फॉर्मेशन ऍड करण्यासाठी सेवाकेंद्रातील संजिवनी प्रमुख यांना संपर्क करा..श्री गुरुदेव");
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
                                            //Toast.makeText(SanjivaniPage.this, "Ya Janganan Number varil vykati hi sadguru nagar sevakendratil kontya padd var nahi .ShreeGurudev" + m_text, Toast.LENGTH_SHORT).show();
                                        }
                                        if(m_text.equals(vishnu))
                                        {
                                            Intent intent=new Intent(getApplicationContext(), AddMemberToTheJivanRkshak.class);
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
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if(adapterView.getItemAtPosition(i)=="भूदान संकल्प पूर्ती") {

                        Toast.makeText(SanjivaniPage.this, ""+adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                        recyclerView=findViewById(R.id.sanjivanirecyclerView);
                        sanjivanireference = FirebaseDatabase.getInstance().getReference("BhudanSanklpa");
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        donarDetails.setVisibility(View.INVISIBLE);
                        bhudanDataArrayList= new ArrayList<>();
                        bhudanAdapter = new BhudanAdapter(getApplicationContext(), bhudanDataArrayList);
                        recyclerView.setAdapter(bhudanAdapter);
                        sanjivanireference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                bhudanDataArrayList.clear();
                                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                                {
                                    BhudanData user = dataSnapshot.getValue(BhudanData.class);
                                    bhudanDataArrayList.add(user);
                                }
                                bhudanAdapter.notifyDataSetChanged();

                            }
                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder my=new AlertDialog.Builder(SanjivaniPage.this);
                                my.setTitle("आपका जनगणना नंबर प्रविष्ट करे.");
                                final EditText j=new EditText(SanjivaniPage.this);
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
                                            AlertDialog.Builder display=new AlertDialog.Builder(SanjivaniPage.this);
                                            display.setTitle("Error");
                                            display.setMessage("इन्फॉर्मेशन ऍड करण्यासाठी सेवाकेंद्रातील संजिवनी प्रमुख यांना संपर्क करा..श्री गुरुदेव");
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
                                            //Toast.makeText(SanjivaniPage.this, "Ya Janganan Number varil vykati hi sadguru nagar sevakendratil kontya padd var nahi .ShreeGurudev" + m_text, Toast.LENGTH_SHORT).show();
                                        }
                                        if(m_text.equals(vishnu))
                                        {
                                            Intent intent=new Intent(getApplicationContext(), AddMemberToBhudan.class);
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
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if(adapterView.getItemAtPosition(i)=="Select-->") {
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                donarDetails.setVisibility(View.INVISIBLE);
                                AlertDialog.Builder alert=new AlertDialog.Builder(SanjivaniPage.this);
                                alert.setTitle("Error");
                                alert.setMessage("Please select the Yogana");
                                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                                alert.create().show();
                            }
                        });
                    }
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                ///////////////////////////////////////////////////////////////////////////////////////////////////////
                    if(adapterView.getItemAtPosition(i)=="नंद दत्तक पालक योजना"){

                        Toast.makeText(getApplicationContext(), ""+adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                        recyclerView=findViewById(R.id.sanjivanirecyclerView);
                        sanjivanireference = FirebaseDatabase.getInstance().getReference("NandDattak");
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        nandDataArrayList= new ArrayList<>();
                        adapter = new Adapter(getApplicationContext(), nandDataArrayList);
                        recyclerView.setAdapter(adapter);
                        sanjivanireference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                nandDataArrayList.clear();
                                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                                {
                                    NandData user = dataSnapshot.getValue(NandData.class);
                                    nandDataArrayList.add(user);
                                }
                                adapter.notifyDataSetChanged();

                            }
                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });
                        add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder my=new AlertDialog.Builder(SanjivaniPage.this);
                                my.setTitle("आपका जनगणना नंबर प्रविष्ट करे.");
                                final EditText j=new EditText(SanjivaniPage.this);
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
                                            AlertDialog.Builder display=new AlertDialog.Builder(SanjivaniPage.this);
                                            display.setTitle("Error");
                                            display.setMessage("इन्फॉर्मेशन ऍड करण्यासाठी सेवाकेंद्रातील संजिवनी प्रमुख यांना संपर्क करा..श्री गुरुदेव");
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
                                        }
                                        if(m_text.equals(vishnu))
                                        {
                                            Intent intent=new Intent(getApplicationContext(), Nanddatak.class);
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
                    //////////////////////////////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////////////////////////////////////
                 }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void gotoUrl(String s){
        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}