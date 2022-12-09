
package com.vishnu.sadgurunagarsevakendra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.vishnu.sadgurunagarsevakendra.LoadingDialogBox.LoadingDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import vishnu.sadgurunagarsevakendra.R;

public class Login extends AppCompatActivity {

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    Animation topAnim;
    EditText userid,password;
    CheckBox checkBox;
    Button loginBtn;
    TextView register,loginanimation;
    FirebaseAuth fAuth;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userid=findViewById(R.id.userid);
        password=findViewById(R.id.password);
        checkBox=findViewById(R.id.remberme);
        loginBtn=findViewById(R.id.login1);
        loginanimation=findViewById(R.id.animationlogin);
        register=findViewById(R.id.register);
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        loginanimation.setAnimation(topAnim);
        fAuth=FirebaseAuth.getInstance();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);

        final LoadingDialog loadingDialog=new LoadingDialog(Login.this);
        sp=getSharedPreferences("loginDetails",MODE_PRIVATE);

        String check= sp.getString("remember","");
                    if(check.equals("true"))
                    {
                        //Getting details from SharedPreferences (manje userid ani jo password save kela aahe na to parat magvych aahe)

                        sp=getApplicationContext().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
                        String u=sp.getString("userid","");
                        String p=sp.getString("password","");
                        userid.setText(u);
                        password.setText(p);
                    }

        ///////////////////////////////checkbox code/////////////////////////////////////////////////////////
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                String username=userid.getText().toString();
                String passwordname=password.getText().toString();
                //stroing to the sharedPreference
                if(compoundButton.isChecked())
                {
                    //Storing to the sharedpreferneces
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("userid", username);
                    editor.putString("password", passwordname);
                    editor.putString("remember","true");
                    editor.apply();
                }
                else
                {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("remember","false");
                    editor.apply();
                }
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterUsserInfo.class));
                finish();
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////////////////////////////////////
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String user=userid.getText().toString();
                String passwordr=password.getText().toString();

                if(TextUtils.isEmpty(passwordr))
                {
                    userid.setError("Email Is Requried For Login");
                    return;
                }
                if(TextUtils.isEmpty(passwordr))
                {
                    password.setError("Passwprd is Requried For Login");
                    return;
                }
                if(passwordr.length() < 6){
                    password.setError("password must 6 charter");
                    return;
                }
                loadingDialog.startdialog();

                //Authemticate the user
                    fAuth.signInWithEmailAndPassword(user,passwordr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull  Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                //Toast.makeText(Login.this, "Login SucessFully...!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),AfterLoginPage.class));
                            }
                            else
                            {
                                loadingDialog.Dismissdialog();
                                AlertDialog.Builder builder=new AlertDialog.Builder(Login.this);
                                builder.setTitle("Error");
                                builder.setMessage("Please Check The Internet Connection and Enter Correct Details");
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                                builder.create().show();
                            }
                        }
                    });
            }

        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis())
        {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit from APP!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}