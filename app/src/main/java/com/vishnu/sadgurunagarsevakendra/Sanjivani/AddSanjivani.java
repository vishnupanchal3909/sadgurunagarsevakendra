package com.vishnu.sadgurunagarsevakendra.Sanjivani;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vishnu.sadgurunagarsevakendra.LoadingDialogBox.LoadingDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vishnu.sadgurunagarsevakendra.R;

public class AddSanjivani extends AppCompatActivity {

    EditText name,mobile,amount;
    Button submit;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sanjivani);
        name=findViewById(R.id.sanjivani_name);
        mobile=findViewById(R.id.Mobile_Number_sanjivani);
        amount=findViewById(R.id.amount);
        submit=findViewById(R.id.submit_sanjivani);
        final LoadingDialog loadingDialog=new LoadingDialog(AddSanjivani.this);
        databaseReference= FirebaseDatabase.getInstance().getReference("Sanjivani");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SANJIVANINAME,SANJIVANIMOBILE,SANJIVANIAMOUNT;
                SANJIVANINAME=name.getText().toString();
                SANJIVANIMOBILE=mobile.getText().toString();
                SANJIVANIAMOUNT=amount.getText().toString();
                if(TextUtils.isEmpty(SANJIVANINAME))
                {
                    name.setError("Enter Name");
                    return;
                }
                if(TextUtils.isEmpty(SANJIVANIMOBILE))
                {
                    mobile.setError("Enter Mobile Number");
                    return;
                }
                if(TextUtils.isEmpty(SANJIVANIAMOUNT))
                {
                    amount.setError("Enter Amount");
                    return;
                }
                loadingDialog.startdialog();
                SanjivaniUserClassHelper sanjivaniUserClassHelper=new SanjivaniUserClassHelper(SANJIVANINAME,SANJIVANIMOBILE,SANJIVANIAMOUNT);

                databaseReference.push().setValue(sanjivaniUserClassHelper);
                Toast.makeText(AddSanjivani.this, "Added SuccessFully...!", Toast.LENGTH_SHORT).show();
                loadingDialog.Dismissdialog();
            }
        });

    }
}