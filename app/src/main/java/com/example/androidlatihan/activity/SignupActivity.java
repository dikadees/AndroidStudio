package com.example.androidlatihan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androidlatihan.R;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText username, fullname, email, password;
    private Button daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.username);
        fullname = (EditText) findViewById(R.id.fullname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        daftar = (Button) findViewById(R.id.btn_signup);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int getUsername = username.getText().toString().length();
                int getFullname = fullname.getText().toString().length();
                int getEmail = email.getText().toString().length();
                int getPassword = password.getText().toString().length();


                if (getUsername == 0 && getFullname==0 && getEmail == 0 && getPassword == 0){
                    username.setError("Username harus diisi");
                    fullname.setError("Nama tidak boleh kosong");
                    email.setError("Email masih kosong");
                    password.setError("Password harus diisi");
                    Toast.makeText(getApplicationContext(), "Registrasi" +
                            "gagal", Toast.LENGTH_SHORT).show();
                } else if(getUsername == 0){
                    username.setError("Username harus diisi");
                    Toast.makeText(getApplicationContext(), "Masukkan" +
                            "username", Toast.LENGTH_SHORT).show();
                } else if(getFullname == 0){
                    fullname.setError("Full name tidak boleh kosong");
                    Toast.makeText(getApplicationContext(), "Masukkan" +
                            "full name", Toast.LENGTH_SHORT).show();
                } else if (getEmail == 0){
                    email.setError("Email masih kosong");
                    Toast.makeText(getApplicationContext(), "Masukkan" +
                            "email", Toast.LENGTH_SHORT).show();
                } else if (getPassword == 0){
                    password.setError("Password harus diisi");
                    Toast.makeText(getApplicationContext(), "Masukkan" +
                            "password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Registrasi" +
                            "berhasil", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Spinner colorSpinner = findViewById(R.id.spinnerRole);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.role_item,
                R.layout.color_spinner
        );
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        colorSpinner.setAdapter(adapter);
        colorSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



}