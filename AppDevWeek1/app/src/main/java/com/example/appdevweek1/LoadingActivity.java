package com.example.appdevweek1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.widget.ProgressBar;

public class LoadingActivity extends AppCompatActivity {
    private AlertDialog alertDialog;
    private Activity activity;

    public LoadingActivity(Activity activity) {
        this.activity = activity;
    }

    public void startDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.activity_loading, null));
        builder.setCancelable(false);

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void stopDialog(){
        alertDialog.dismiss();
    }
}