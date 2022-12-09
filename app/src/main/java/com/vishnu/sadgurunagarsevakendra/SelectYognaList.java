package com.vishnu.sadgurunagarsevakendra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.vishnu.sadgurunagarsevakendra.Sanjivani.SanjivaniPage;

import vishnu.sadgurunagarsevakendra.R;

public class SelectYognaList extends AppCompatActivity {

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_yogna_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        spinner=findViewById(R.id.spinner);
        String[] item={"One","Two","Three","Four"};
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SelectYognaList.this, ""+adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                if(adapterView.getItemAtPosition(i)== "One"){
                Intent intent=new Intent(getApplicationContext(), SanjivaniPage.class);
                startActivity(intent);
            }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}