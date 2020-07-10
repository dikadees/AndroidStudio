package com.example.androidlatihan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnlogin, btnsignup;
    private EditText username, password;

    RelativeLayout rellay1, rellay2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogin = findViewById(R.id.btnlogin);
        btnsignup = findViewById(R.id.btnsignup);
        username = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 2000); // timeout for splash


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().length()==0 && password.getText().toString().length()==0){
                    username.setError("Username harus diisi");
                    password.setError("Password harus diisi");
                } else if (username.getText().toString().length()==0){
                    username.setError("Password harus diisi");
                } else if (password.getText().toString().length()==0){
                    password.setError("Password harus diisi");
                } else {
                    Toast.makeText(getApplicationContext(), "Registrasi" +
                            "berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, welcome.class);
                    startActivity(intent);
                }
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // action
                Intent intent = new Intent(MainActivity.this, signup.class);
                startActivity(intent);
            }
        });


    }
}