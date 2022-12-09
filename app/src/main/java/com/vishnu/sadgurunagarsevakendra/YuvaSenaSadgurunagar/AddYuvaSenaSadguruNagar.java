package com.vishnu.sadgurunagarsevakendra.YuvaSenaSadgurunagar;

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

public class AddYuvaSenaSadguruNagar extends AppCompatActivity {

    EditText yuvasenaName,yuvasenaMobile,yuvasenaAge,yuvasenaStarDate,yuvasenaJanganan;
    Button submit;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_yuva_sena_sadguru_nagar);
        yuvasenaName=findViewById(R.id.nameyuvasenasadgurunagar);
        yuvasenaMobile=findViewById(R.id.mobilenumberyuvasenasadgurunagar);
        yuvasenaAge=findViewById(R.id.ageyuvasenasadgurunagar);
        yuvasenaStarDate=findViewById(R.id.birthdateyuvasenasadgurunagar);
        yuvasenaJanganan=findViewById(R.id.jangananumberyuvasenasadgurunagar);
        submit=findViewById(R.id.yuvasenasadgurunagar);
        final LoadingDialog loadingDialog=new LoadingDialog(AddYuvaSenaSadguruNagar.this);
        databaseReference= FirebaseDatabase.getInstance().getReference("YuvaSena");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String YUVANAME=yuvasenaName.getText().toString();
                String YUVAMOBILE=yuvasenaMobile.getText().toString();
                String YUVAJNUMBER=yuvasenaJanganan.getText().toString();
                String YUVAAGE=yuvasenaAge.getText().toString();
                String YUVADATE=yuvasenaStarDate.getText().toString();

                if(TextUtils.isEmpty(YUVANAME))
                {
                    yuvasenaName.setError("Enter Name");
                    return;
                }
                if(TextUtils.isEmpty(YUVAMOBILE))
                {
                    yuvasenaMobile.setError("Enter Mobile Number");
                    return;
                }
                if(TextUtils.isEmpty(YUVAAGE))
                {
                    yuvasenaAge.setError("Enter Age");
                    return;
                }
                if(TextUtils.isEmpty(YUVAJNUMBER))
                {
                    yuvasenaJanganan.setError("Enter Jangnan Number");
                    return;
                }
                if(TextUtils.isEmpty(YUVADATE))
                {
                    yuvasenaStarDate.setError("Enter Date");
                    return;
                }
                loadingDialog.startdialog();
                YuvaSenaUserClassHelper yuvaSenaUserClassHelper=new YuvaSenaUserClassHelper(YUVANAME,YUVAMOBILE,YUVAAGE,YUVADATE,YUVAJNUMBER);
                databaseReference.push().setValue(yuvaSenaUserClassHelper);
                Toast.makeText(AddYuvaSenaSadguruNagar.this, "Added SuccessFully...!", Toast.LENGTH_SHORT).show();
                loadingDialog.Dismissdialog();
            }
        });


    }
}