package com.vishnu.sadgurunagarsevakendra;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnu.sadgurunagarsevakendra.DataBase.DbClassHelper;
import com.vishnu.sadgurunagarsevakendra.LoadingDialogBox.LoadingDialog;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import vishnu.sadgurunagarsevakendra.R;


public class RegisterUsserInfo extends AppCompatActivity {

    EditText registerName,registerhome,registerMobile,registerjanganan,registerEmail,registerPassword;
    Button submitregister;
    FirebaseAuth fAuth;
    private static final int TIME_DELAY = 1000;
    private static long back_pressed;
    DatabaseReference reference;
    TextView loginhere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_usser_info);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        registerName=findViewById(R.id.nameregister);
        registerhome=findViewById(R.id.address_register);
        registerMobile=findViewById(R.id.mobilenumberregister);
        registerjanganan=findViewById(R.id.jnumberregister);
        registerEmail=findViewById(R.id.email_register);
        registerPassword=findViewById(R.id.password_register);
        submitregister=findViewById(R.id.submitregister);
        loginhere=findViewById(R.id.loginhereregisterpage);
        loginhere=findViewById(R.id.loginhereregisterpage);
        DbClassHelper helper=new DbClassHelper(getApplicationContext());
        final LoadingDialog loadingDialog=new LoadingDialog(RegisterUsserInfo.this);
        /////////////////////////////////////////////////////////////
        fAuth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("User");
        ////////////////////////////////////////////////////////////////////////////////
        submitregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=registerName.getText().toString();
                String address=registerhome.getText().toString();
                String mobile=registerMobile.getText().toString();
                String registerjangnannumber=registerjanganan.getText().toString();
                String email=registerEmail.getText().toString();
                String password=registerPassword.getText().toString();

                if(TextUtils.isEmpty(name))
                {
                    registerName.setError("नाम प्रविष्ट करे.");
                    return;
                }
                if(TextUtils.isEmpty(address))
                {
                    registerhome.setError("पत्ता प्रविष्ट करे.");
                    return;
                }
                if(TextUtils.isEmpty(mobile))
                {
                    registerMobile.setError("मोबाईल नंबर प्रविष्ट करे.");
                    return;
                }
                if(TextUtils.isEmpty(registerjangnannumber))
                {
                    registerjanganan.setError("जनगणना नंबर प्रविष्ट करे.");
                    return;
                }
                if(TextUtils.isEmpty(email))
                {
                    registerEmail.setError("ईमेल प्रविष्ट करे.");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    registerPassword.setError("पासवर्ड प्रविष्ट करे.");
                    return;
                }
                if(password.length() < 6)
                {
                    registerPassword.setError("password must Be Grater Than 6 charter");
                    return;
                }
                loadingDialog.startdialog();

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            HashMap<String,Object> user=new HashMap<>();
                            user.put("name",name);
                            user.put("address",address);
                            user.put("mobile",mobile);
                            user.put("jnumber",registerjangnannumber);
                            user.put("email",email);
                            user.put("password",password);
                            //store to realtime databse to firebase
                            reference.push().setValue(user);
                            //store to SqlLite  Database helper
                            helper.insert(name,address,mobile,registerjangnannumber,email,password);
                            AlertDialog.Builder builder=new AlertDialog.Builder(RegisterUsserInfo.this);
                            builder.setTitle("तुमचे रजिस्ट्रेशन यशस्वी झाले.");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builder.create().show();
                            Intent intent=new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                        }
                        else
                        {
                            loadingDialog.Dismissdialog();
                            AlertDialog.Builder builder=new AlertDialog.Builder(RegisterUsserInfo.this);
                            builder.setTitle("तुमचे रजिस्ट्रेशन अयशस्वी झाले.");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            builder.create().show();                        }
                    }
                });

                /////////////////////////////////////////////////////
            }
        });
        /////////////////////////////////////////////////////////////////////////////////
        loginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            Intent intent=new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        } else {
            Toast.makeText(getBaseContext(), "Again Back To The Page..!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}