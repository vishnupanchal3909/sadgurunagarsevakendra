package com.vishnu.sadgurunagarsevakendra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.vishnu.sadgurunagarsevakendra.BhaktaGan.BhaktaGanPage;
import com.vishnu.sadgurunagarsevakendra.Commity.CommityPage;
import com.vishnu.sadgurunagarsevakendra.Dharmakshetra.dharmakshetra;
import com.vishnu.sadgurunagarsevakendra.LoadingDialogBox.LoadingDialog;
import com.vishnu.sadgurunagarsevakendra.Report.ReportPage;
import com.vishnu.sadgurunagarsevakendra.Sanjivani.SanjivaniPage;
import com.vishnu.sadgurunagarsevakendra.YuvaSenaSadgurunagar.YuvaSenaSadgurunagarPage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vishnu.sadgurunagarsevakendra.R;

public class AfterLoginPage extends AppCompatActivity {

    FirebaseDatabase root;
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    DatabaseReference databaseReference;
    ImageView commityLogo,yuvasenaLogo,dharmakshetraLogo,sanjivanilogo,bhaktlogo,reportlogo;
    Animation topAnim;
//    TextView logout;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        ////////////////////////////////////////////////////////
            toolbar=findViewById(R.id.toolbar);
            drawerLayout=findViewById(R.id.drawerlayout);
            navigationView=findViewById(R.id.navigationview);
            reportlogo=findViewById(R.id.reportimageview);
            yuvasenaLogo=findViewById(R.id.yuvasena);
            dharmakshetraLogo=findViewById(R.id.dharmakshetra);
            sanjivanilogo=findViewById(R.id.sanjivani);
            commityLogo=findViewById(R.id.commity);
//            logout=findViewById(R.id.logout);
        ////////////////////////////////////////////////////////////
            topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
//            logout.setAnimation(topAnim);
//            commityLogo.setAnimation(topAnim);
//            yuvasenaLogo.setAnimation(topAnim);
//            dharmakshetraLogo.setAnimation(topAnim);
//            sanjivanilogo.setAnimation(topAnim);
//            reportlogo.setAnimation(topAnim);
            bhaktlogo=findViewById(R.id.button2);
//            bhaktlogo.setAnimation(topAnim);
            final LoadingDialog loadingDialog=new LoadingDialog(AfterLoginPage.this);
        //////////////////////////////////////////////////////////////////////////////////////////////
            root=FirebaseDatabase.getInstance();
            databaseReference=root.getReference();
        //////////////////////////////////////////////////////////////////////////////////////////////
        setSupportActionBar(toolbar);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                 if(id == R.id.bhajan)
                {
                    Toast.makeText(getApplicationContext(), "bhajan", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.aarati)
                {
                    Toast.makeText(getApplicationContext(), "aarati", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.about)
                {
                    Intent intent=new Intent(getApplicationContext(),Information.class);
                    startActivity(intent);
                }
                else if(id == R.id.logout1)
                {
                    loadingDialog.startdialog();
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////////
        commityLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder my=new AlertDialog.Builder(AfterLoginPage.this);
//                my.setTitle("Enter Jangnan Number");
//                final EditText j=new EditText(AfterLoginPage.this);
//                j.setInputType(InputType.TYPE_CLASS_NUMBER);
//                my.setView(j);
//                my.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String m_text = j.getText().toString();
//                        Toast.makeText(AfterLoginPage.this, "Positive signal:="+m_text, Toast.LENGTH_SHORT).show();
//                    }
//                });
//                my.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
//                    }
//                });
//                my.create().show();
                Intent intent=new Intent(getApplicationContext(), CommityPage.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadingDialog.startdialog();
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(getApplicationContext(),Login.class));
//            }
//        });
        //////////////////////////////////////////////////////////////////////////////////////////////
        yuvasenaLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), YuvaSenaSadgurunagarPage.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////
        dharmakshetraLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), dharmakshetra.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////
        sanjivanilogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), SanjivaniPage.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////
        bhaktlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), BhaktaGanPage.class);
                startActivity(intent);
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////
        reportlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ReportPage.class);
                startActivity(intent);
            }
        });



    }
    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}