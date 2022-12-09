package com.vishnu.sadgurunagarsevakendra.Commity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import vishnu.sadgurunagarsevakendra.R;

public class AddMemberToTheCommity extends AppCompatActivity {


    EditText name,mobile;
    String saveCurrentDate, saveCurrentTime;
    AutoCompleteTextView pad;
    TextInputLayout Pad_Text;
    Button submit;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;
    CircleImageView circleImageView;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef,databaseReference;
    String NAME,MOBILE, PAD ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member_to_the_commity);
        name = findViewById(R.id.name);
        mobile = findViewById(R.id.Mobile_Number);
        Pad_Text = findViewById(R.id.pad_text_name);
        pad = findViewById(R.id.Pad_name);
        submit = findViewById(R.id.submit);
        circleImageView = findViewById(R.id.commitymemberimage);


        String[] item = new String[]{
                "सेवाकेंद्र अध्यक्ष", "सेवाकेंद्र महिला अध्यक्ष", " मेजर", "युवा अध्यक्ष ", "सचिव", "देणगी प्रमुख ", "संजिवनी प्रमुख", "प्रसिध्दी प्रमुख", " जनगणना प्रमुख", "धर्मक्षेत्र प्रमुख", "अध्यात्मिक प्रमुख ", " BIN प्रमुख", "सामाजिक प्रमुख", "शिबिर प्रमुख"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                AddMemberToTheCommity.this, R.layout.dropdownmenu, item
        );
        pad.setAdapter(adapter);

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GalleryPick);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 databaseReference = FirebaseDatabase.getInstance().getReference("Student");

                //Get All Value

                String NAME = name.getText().toString();
                String MOBILE = mobile.getText().toString();
                String PAD = pad.getText().toString();


                if (TextUtils.isEmpty(NAME)) {
                    name.setError("Enter Name");
                    return;
                }
                if (TextUtils.isEmpty(MOBILE)) {
                    mobile.setError("Enter Mobile Number");
                    return;
                }
                if (TextUtils.isEmpty(PAD)) {
                    pad.setError("Enter Pad Name");
                    return;
                }

                UserClassHelper user = new UserClassHelper(NAME, MOBILE, PAD);

                databaseReference.push().setValue(user);
                Toast.makeText(AddMemberToTheCommity.this, "Add SuccesFully..!", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(getApplicationContext(), CommityPage.class);
                startActivity(intent);

            }
        });
    }

}





