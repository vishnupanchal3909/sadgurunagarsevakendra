package com.vishnu.sadgurunagarsevakendra.NandDatak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vishnu.sadgurunagarsevakendra.R;

public class Nanddatak extends AppCompatActivity {

    EditText name,mobile;
    Button submit;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nanddatak);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        name=findViewById(R.id.name_nanddattak);
        mobile=findViewById(R.id.Mobile_Number_nanddattak);
        submit=findViewById(R.id.submit_nanddattak);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference= FirebaseDatabase.getInstance().getReference("NandDattak");
//////////////////////////////////////////////////////////////////////////////////////////
                String n=name.getText().toString();
                String m=mobile.getText().toString();

                if(TextUtils.isEmpty(n)){
                    name.setError("Enter Name");
                    return;
                }
                if(TextUtils.isEmpty(m)){
                    mobile.setError("Enter Mobile Number");
                }

                AddNandDattak nand=new AddNandDattak(n,m);
                reference.push().setValue(nand);
                Toast.makeText(getApplicationContext(), "Added SuccesFully...!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}