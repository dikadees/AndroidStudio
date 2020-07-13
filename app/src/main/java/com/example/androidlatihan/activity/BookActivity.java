package com.example.androidlatihan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidlatihan.R;
import com.getbase.floatingactionbutton.FloatingActionButton;

public class BookActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton1,floatingActionButton2,floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        floatingActionButton1 = findViewById(R.id.fabAdd);
        floatingActionButton2 = findViewById(R.id.fabEdit);
        floatingActionButton  = findViewById(R.id.fabHome);

        openHomeFragment();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // action
                openAddFragment();
            }
        });

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // action
                openAddFragment();
            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // action
                openEditFragment();
            }
        });



    }


    private void openHomeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //actifityFragment
        HomeFragment strCode = new HomeFragment();
        fragmentTransaction.replace(R.id.content, strCode, "home fragment");
        fragmentTransaction.commit();


    }
    private void openEditFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //actifityFragment
        EditFragment strCode = new EditFragment();
        fragmentTransaction.replace(R.id.content, strCode, "edit fragment");
        fragmentTransaction.commit();


    }
    private void openAddFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //actifityFragment
        AddFragment strCode = new AddFragment();
        fragmentTransaction.replace(R.id.content, strCode, "add fragment");
        fragmentTransaction.commit();


    }

}