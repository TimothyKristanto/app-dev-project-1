package com.example.appdevweek1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Model.DataUser;

public class MainActivity extends AppCompatActivity implements MainRVAdapter.UserViewholderClick {
    private RecyclerView recyclerviewMain;
    private MainRVAdapter adapter;
    private ArrayList<DataUser> listUser;
    private FloatingActionButton btnTambahUserMain;
    private TextView txtNoDataMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVar();
        getIntentData();
        initAdapter();
        setListener();
    }

    private void initVar(){
        recyclerviewMain = findViewById(R.id.recyclerviewMain);
        listUser = new ArrayList<>();
        btnTambahUserMain = findViewById(R.id.btnTambahUserMain);
        txtNoDataMain = findViewById(R.id.txtNoDataMain);
    }

    private void initAdapter(){
        adapter = new MainRVAdapter(listUser, this);
        recyclerviewMain.setAdapter(adapter);
    }

    private void setListener(){
        btnTambahUserMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditUserActivity.class);
                intent.putParcelableArrayListExtra("listUser", listUser);
                intent.putExtra("isEditing", false);
                startActivity(intent);
            }
        });
    }

    private void getIntentData(){
        Intent intent = this.getIntent();
        if(intent.getParcelableArrayListExtra("listUser") != null){
            listUser = intent.getParcelableArrayListExtra("listUser");
        }

        if(listUser.size() > 0){
            txtNoDataMain.setVisibility(View.INVISIBLE);
        }else{
            txtNoDataMain.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(int position) {
        // intent to UserDetailsActivity.java
        Intent intent = new Intent(MainActivity.this, UserDetailsActivity.class);
        intent.putParcelableArrayListExtra("listUser", listUser);
        intent.putExtra("indexUser", position);
        startActivity(intent);
    }
}