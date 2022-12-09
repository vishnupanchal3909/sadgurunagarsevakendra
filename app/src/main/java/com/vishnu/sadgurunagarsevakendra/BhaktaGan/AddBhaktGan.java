package com.vishnu.sadgurunagarsevakendra.BhaktaGan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.vishnu.sadgurunagarsevakendra.LoadingDialogBox.LoadingDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vishnu.sadgurunagarsevakendra.R;

public class AddBhaktGan extends AppCompatActivity {

    AutoCompleteTextView sex;
    EditText name,mobile,home,jnum;
    DatabaseReference reference;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bhakt_gan);
        sex=findViewById(R.id.gender_name);
        name=findViewById(R.id.namebhaktagan);
        final LoadingDialog loadingDialog=new LoadingDialog(AddBhaktGan.this);
        mobile=findViewById(R.id.Mobile_Number_bhakta);
        home=findViewById(R.id.address_bhakta);
        jnum=findViewById(R.id.jnumber_bhakta);
        submit=findViewById(
                R.id.submitbhakt);
        String genders[]={
          "Male","Female","Others"
        };
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplication(),R.layout.bhaktadropdown,genders);
        sex.setAdapter(adapter);

        ///////////////////////////////////////////////////////////////////////////////
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference= FirebaseDatabase.getInstance().getReference("BhaktGan");
                String NAME,MOBILE,HOME,GENDER,JNUMBER;
                NAME=name.getText().toString();
                MOBILE=mobile.getText().toString();
                HOME=home.getText().toString();
                GENDER=sex.getText().toString();
                JNUMBER=jnum.getText().toString();

                if(TextUtils.isEmpty(NAME)){
                    name.setError("नाम प्रविष्ट करे.");
                    return;
                }
                if(TextUtils.isEmpty(MOBILE)){
                    mobile.setError("मोबाईल नंबर प्रविष्ट करे.");
                    return;
                }
                if(TextUtils.isEmpty(GENDER)){
                    sex.setError(" लिंग प्रविष्ट करे.");
                    return;
                }
                if(TextUtils.isEmpty(JNUMBER)){
                    jnum.setError("जनगणना नंबर प्रविष्ट करे.");
                    return;
                }
                if(TextUtils.isEmpty(HOME)){
                    home.setError("पत्ता  प्रविष्ट करे.");
                    return;
                }
                loadingDialog.startdialog();
                BhaktUserClassHelper bhaktUserClassHelper=new BhaktUserClassHelper(NAME,MOBILE,JNUMBER,HOME,GENDER);
                reference.push().setValue(bhaktUserClassHelper);
                Toast.makeText(AddBhaktGan.this, "Added SucessFully...!", Toast.LENGTH_SHORT).show();
                loadingDialog.Dismissdialog();

            }
        });
    }
}