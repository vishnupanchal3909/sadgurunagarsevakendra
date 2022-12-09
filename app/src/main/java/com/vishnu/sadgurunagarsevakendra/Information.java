package com.vishnu.sadgurunagarsevakendra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import vishnu.sadgurunagarsevakendra.R;

public class Information extends AppCompatActivity {

    private static final int TIME_DELAY = 1000;
    private static long back_pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
    }
//    @Override
//    public void onBackPressed() {
//        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
//            Intent intent=new Intent(getApplicationContext(), AfterLoginPage.class);
//            startActivity(intent);
//        } else {
//            Toast.makeText(getBaseContext(), "Again Back To The Page..!",
//                    Toast.LENGTH_SHORT).show();
//        }
//        back_pressed = System.currentTimeMillis();
//    }
}