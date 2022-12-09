package com.vishnu.sadgurunagarsevakendra.Dharmakshetra;

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

public class AddDharmkshetra extends AppCompatActivity {

    EditText dharmname,dharmmobile,dharmstartdate,dharmenddate,dharmjangnan;
    Button submit;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dharmkshetra);
        dharmname=findViewById(R.id.namedharmkshetra);
        dharmmobile=findViewById(R.id.Mobile_Number_dharmkshetra);
        dharmstartdate=findViewById(R.id.startDate_dhsrmkshetra);
        dharmenddate=findViewById(R.id.EndDateDharmkshetra);
        dharmjangnan=findViewById(R.id.jangana_Number_dharmkshetra);
        final LoadingDialog loadingDialog=new LoadingDialog(AddDharmkshetra.this);
        submit=findViewById(R.id.submitdharmkshetra);
        reference= FirebaseDatabase.getInstance().getReference("Dharmkshetra");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NAMEDHARMKSHETRA=dharmname.getText().toString();
                String MOBILEDHARMKSHETRA=dharmmobile.getText().toString();
                String STARTDATEDHARMKSHETRA=dharmstartdate.getText().toString();
                String ENDDATEDHARMKSHETRA=dharmenddate.getText().toString();
                String JANGANADHARMKSHETRA=dharmjangnan.getText().toString();


                if(TextUtils.isEmpty(NAMEDHARMKSHETRA))
                {
                    dharmname.setError("Enter Name");
                    return;
                }
                if(TextUtils.isEmpty(MOBILEDHARMKSHETRA))
                {
                    dharmmobile.setError("Enter Mobile Number");
                    return;
                }
                if(TextUtils.isEmpty(STARTDATEDHARMKSHETRA))
                {
                    dharmstartdate.setError("Enter Starting Date");
                    return;

                }
                if(TextUtils.isEmpty(ENDDATEDHARMKSHETRA))
                {
                    dharmenddate.setError("Enter Ending Date");
                    return;
                }
                if(TextUtils.isEmpty(JANGANADHARMKSHETRA))
                {
                    dharmjangnan.setError("Enter Jangnana Number");
                    return;
                }
                loadingDialog.startdialog();

                DharmkshetraUserClassHelper dharmkshetraUserClassHelper=new DharmkshetraUserClassHelper(NAMEDHARMKSHETRA,MOBILEDHARMKSHETRA,STARTDATEDHARMKSHETRA,ENDDATEDHARMKSHETRA,JANGANADHARMKSHETRA);
                reference.push().setValue(dharmkshetraUserClassHelper);
                Toast.makeText(AddDharmkshetra.this, "Added SuccesFully...!", Toast.LENGTH_SHORT).show();
                loadingDialog.Dismissdialog();

            }
        });
    }
}