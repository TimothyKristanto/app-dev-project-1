package com.example.appdevweek1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import Model.DataUser;

public class AddEditUserActivity extends AppCompatActivity {
    private TextInputLayout txtInputNamaAddUser, txtInputUmurAddUser, txtInputAlamatAddUser;
    private Button btnAddEditAddEditUser;
    private Toolbar toolbarAddUser;
    private ArrayList<DataUser> listUser;
    private int indexUser = -1;
    private LoadingActivity loadingActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        initVar();
        getIntentData();
        setListener();
    }

    private void initVar() {
        txtInputNamaAddUser = findViewById(R.id.txtInputNamaAddUser);
        txtInputUmurAddUser = findViewById(R.id.txtInputUmurAddUser);
        txtInputAlamatAddUser = findViewById(R.id.txtInputAlamatAddUser);
        btnAddEditAddEditUser = findViewById(R.id.btnAddEditAddEditUser);
        toolbarAddUser = findViewById(R.id.toolbarAddUser);
        listUser = new ArrayList<>();
        loadingActivity = new LoadingActivity(AddEditUserActivity.this);
    }

    private void setListener(){
        btnAddEditAddEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = txtInputNamaAddUser.getEditText().getText().toString().trim();
                String umur = txtInputUmurAddUser.getEditText().getText().toString().trim();
                String alamat = txtInputAlamatAddUser.getEditText().getText().toString().trim();

                if(!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(alamat) && !TextUtils.isEmpty(umur)){
                    loadingActivity.startDialog();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(indexUser == -1){
                                listUser.add(new DataUser(nama, umur, alamat));
                            }else{
                                listUser.set(indexUser, new DataUser(nama, umur, alamat));
                            }
                            Intent intent = new Intent(AddEditUserActivity.this, MainActivity.class);
                            intent.putParcelableArrayListExtra("listUser", listUser);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            loadingActivity.stopDialog();
                            startActivity(intent);
                            finish();
                        }
                    }, 2000);
                }else{
                    Toast.makeText(AddEditUserActivity.this, "Semua data harus terisi!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        toolbarAddUser.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(indexUser == -1) {
                    intent = new Intent(AddEditUserActivity.this, MainActivity.class);
                    intent.putParcelableArrayListExtra("listUser", listUser);
                }else{
                    intent = new Intent(AddEditUserActivity.this, UserDetailsActivity.class);
                    intent.putParcelableArrayListExtra("listUser", listUser);
                    intent.putExtra("indexUser", indexUser);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getIntentData(){
        Intent intent = this.getIntent();
        if(intent.getParcelableArrayListExtra("listUser") != null){
            listUser = intent.getParcelableArrayListExtra("listUser");
        }

        if(intent.getBooleanExtra("isEditing", false) == true && intent.getIntExtra("indexUser", -1) != -1){
            indexUser = intent.getIntExtra("indexUser", -1);
        }

        if(indexUser == -1){
            toolbarAddUser.setTitle("Add user");
            btnAddEditAddEditUser.setText("Add");
        }else{
            toolbarAddUser.setTitle("Edit user");
            btnAddEditAddEditUser.setText("Update data");
            txtInputNamaAddUser.getEditText().setText(listUser.get(indexUser).getNama());
            txtInputUmurAddUser.getEditText().setText(listUser.get(indexUser).getUmur());
            txtInputAlamatAddUser.getEditText().setText(listUser.get(indexUser).getAlamat());
        }
    }
}