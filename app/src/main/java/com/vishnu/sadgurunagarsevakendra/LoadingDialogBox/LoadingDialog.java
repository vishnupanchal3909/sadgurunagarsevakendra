package com.vishnu.sadgurunagarsevakendra.LoadingDialogBox;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import vishnu.sadgurunagarsevakendra.R;


public class LoadingDialog {

    private Activity activity;
    private AlertDialog alertDialog;

    public LoadingDialog(Activity Myactivity) {
        activity = Myactivity;
    }

    public void startdialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate( R.layout.customdialogbox,null));
        builder.setCancelable(false);
        alertDialog=builder.create();
        alertDialog.show();
    }

    public void Dismissdialog(){
        alertDialog.dismiss();
    }
}
