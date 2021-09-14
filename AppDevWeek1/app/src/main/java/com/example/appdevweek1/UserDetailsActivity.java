package com.example.appdevweek1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import Model.DataUser;

public class UserDetailsActivity extends AppCompatActivity {
    private TextView txtNamaUserDetails, txtUmurUserDetails, txtAlamatUserDetails;
    private Toolbar toolbarUserDetails;
    private ArrayList<DataUser> listUser;
    private int indexDataUser;
    private ImageButton imgBtnDeleteUserDetails, imgBtnEditUserDetails;
    private LoadingActivity loadingActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        initVar();
        getIntentData();
        setListener();
        showUserData();
    }

    private void initVar() {
        txtNamaUserDetails = findViewById(R.id.txtNamaUserDetails);
        txtUmurUserDetails = findViewById(R.id.txtUmurUserDetails);
        txtAlamatUserDetails = findViewById(R.id.txtAlamatUserDetails);
        toolbarUserDetails = findViewById(R.id.toolbarUserDetails);
        imgBtnEditUserDetails = findViewById(R.id.imgBtnEditUserDetails);
        imgBtnDeleteUserDetails = findViewById(R.id.imgBtnDeleteUserDetails);
        listUser = new ArrayList<>();
        loadingActivity = new LoadingActivity(UserDetailsActivity.this);
    }

    private void setListener(){
        toolbarUserDetails.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetailsActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra("listUser", listUser);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        imgBtnEditUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetailsActivity.this, AddEditUserActivity.class);
                intent.putParcelableArrayListExtra("listUser", listUser);
                intent.putExtra("indexUser", indexDataUser);
                intent.putExtra("isEditing", true);
                startActivity(intent);
            }
        });

        imgBtnDeleteUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert("Are you sure to delete " + listUser.get(indexDataUser).getNama() + "'s data?");
            }
        });
    }

    private void getIntentData(){
        Intent intent = this.getIntent();
        if(intent.getParcelableArrayListExtra("listUser") != null && intent.getIntExtra("indexUser", -1) != -1){
            indexDataUser = intent.getIntExtra("indexUser", 0);
            listUser = intent.getParcelableArrayListExtra("listUser");
        }
    }

    private void showUserData(){
        txtNamaUserDetails.setText(listUser.get(indexDataUser).getNama());
        txtAlamatUserDetails.setText(listUser.get(indexDataUser).getAlamat());
        txtUmurUserDetails.setText(listUser.get(indexDataUser).getUmur() + " Years Old");
    }

    private void showAlert(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(UserDetailsActivity.this)
                .setTitle("Delete Confirmation")
                .setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        loadingActivity.startDialog();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                listUser.remove(indexDataUser);
                                Intent intent = new Intent(UserDetailsActivity.this, MainActivity.class);
                                intent.putParcelableArrayListExtra("listUser", listUser);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                loadingActivity.stopDialog();
                                startActivity(intent);
                                finish();
                            }
                        }, 2000);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();

        alertDialog.show();
    }
}