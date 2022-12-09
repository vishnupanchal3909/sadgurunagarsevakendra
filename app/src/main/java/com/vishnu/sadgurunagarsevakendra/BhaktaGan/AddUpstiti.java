package com.vishnu.sadgurunagarsevakendra.BhaktaGan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import vishnu.sadgurunagarsevakendra.R;

public class AddUpstiti extends AppCompatActivity {

    EditText yuvasenaName,yuvasenaJanganan;
    Button submit;
    DatabaseReference databaseReference;
    SharedPreferences spp;
    LinearLayout layout;
    TextView yourself,other,attend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upstiti);
        yuvasenaName=findViewById(R.id.nameyuvasenasadgurunagar1);
        yuvasenaJanganan=findViewById(R.id.jangananumberyuvasenasadgurunagar1);
        layout=findViewById(R.id.linearLayout);
        yourself=findViewById(R.id.yourself);
        other=findViewById(R.id.other);
        attend=findViewById(R.id.attend);
        yuvasenaName=findViewById(R.id.nameyuvasenasadgurunagar1);
        yuvasenaJanganan=findViewById(R.id.jangananumberyuvasenasadgurunagar1);
        submit=findViewById(R.id.yuvasenasadgurunagar1);
        databaseReference= FirebaseDatabase.getInstance().getReference("presenty");

        layout.setVisibility(View.INVISIBLE);
        yourself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                other.setTextSize(20);
                layout.setVisibility(View.VISIBLE);
                attend.setText("स्वयं की उपस्थिती");
                spp=getApplicationContext().getSharedPreferences("AttendanceDeatils",Context.MODE_PRIVATE);
                String n=spp.getString("Name","");
                String j=spp.getString("Janganan","");
                yuvasenaName.setText(n);
                yuvasenaJanganan.setText(j);
                yourself.setTextSize(25);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String YUVANAME=yuvasenaName.getText().toString();
                        String YUVAJNUMBER=yuvasenaJanganan.getText().toString();

                        if(TextUtils.isEmpty(YUVANAME))
                        {
                            yuvasenaName.setError("Enter Name");
                            return;
                        }

                        if(TextUtils.isEmpty(YUVAJNUMBER))
                        {
                            yuvasenaJanganan.setError("Enter Jangnan Number");
                            return;
                        }
                        spp=getSharedPreferences("AttendanceDeatils",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=spp.edit();
                        editor.putString("Name",YUVANAME);
                        editor.putString("Janganan",YUVAJNUMBER);
                        editor.apply();
                        AttendanceUserClass yuvaSenaUserClassHelper=new AttendanceUserClass(YUVANAME,YUVAJNUMBER);
                        databaseReference.push().setValue(yuvaSenaUserClassHelper);
                        AlertDialog.Builder builder=new AlertDialog.Builder(AddUpstiti.this);
                        builder.setMessage("तुमची उपस्थिती सद्गुरू नगर सेवाकेंद्र मध्ये लागली आहे.");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        builder.create().show();
                    }
                });


            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yourself.setTextSize(20);
                layout.setVisibility(View.VISIBLE);
                attend.setText("अन्य की उपस्थिती");
                yuvasenaName.setText(null);
                yuvasenaJanganan.setText(null);
                other.setTextSize(25);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String YUVANAME=yuvasenaName.getText().toString();
                        String YUVAJNUMBER=yuvasenaJanganan.getText().toString();

                        if(TextUtils.isEmpty(YUVANAME))
                        {
                            yuvasenaName.setError("Enter Name");
                            return;
                        }

                        if(TextUtils.isEmpty(YUVAJNUMBER))
                        {
                            yuvasenaJanganan.setError("Enter Jangnan Number");
                            return;
                        }
                        AttendanceUserClass yuvaSenaUserClassHelper=new AttendanceUserClass(YUVANAME,YUVAJNUMBER);
                        databaseReference.push().setValue(yuvaSenaUserClassHelper);
                        AlertDialog.Builder builder=new AlertDialog.Builder(AddUpstiti.this);
                        builder.setMessage("तुमची उपस्थिती सद्गुरू नगर सेवाकेंद्र मध्ये लागली आहे.");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        builder.create().show();
                    }
                });


            }
        });

    }
}