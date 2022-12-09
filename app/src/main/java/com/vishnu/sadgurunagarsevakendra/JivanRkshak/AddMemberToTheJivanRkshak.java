package com.vishnu.sadgurunagarsevakendra.JivanRkshak;

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

public class AddMemberToTheJivanRkshak extends AppCompatActivity {

    EditText name,mobile;
    Button submit;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member_to_the_jivan_rkshak);
        name = findViewById(R.id.name_jivan);
        mobile = findViewById(R.id.Mobile_Number_jivan);
        final LoadingDialog loadingDialog=new LoadingDialog(AddMemberToTheJivanRkshak.this);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference= FirebaseDatabase.getInstance().getReference("JivanRkshak");

                //Get All Value

                String NAME=name.getText().toString();
                String MOBILE=mobile.getText().toString();



                if(TextUtils.isEmpty(NAME))
                {
                    name.setError("Enter Name");
                    return;
                }
                if(TextUtils.isEmpty(MOBILE))
                {
                    mobile.setError("Enter Mobile Number");
                    return;
                }

                loadingDialog.startdialog();
                JivanClassHelper user=new JivanClassHelper(NAME,MOBILE);

                databaseReference.push().setValue(user);
                Toast.makeText(getApplicationContext(), "Add SuccesFully..!", Toast.LENGTH_SHORT).show();
                loadingDialog.Dismissdialog();
            }
        });
    }
}