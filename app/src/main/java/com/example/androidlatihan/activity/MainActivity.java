package com.example.androidlatihan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.androidlatihan.R;
import com.example.androidlatihan.api.JsonRestApi;
import com.example.androidlatihan.model.LoginModel;
import com.example.androidlatihan.model.LoginResponse;
import com.example.androidlatihan.utility.RetrofitUtility;
import com.example.androidlatihan.utility.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btnlogin, btnsignup;
    private EditText username, password;
    private Retrofit retrofit;

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


        //inizialize
        btnlogin = findViewById(R.id.btnlogin);
        btnsignup = findViewById(R.id.btnsignup);
        username = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);

        //
        Utility.askPermission(this);
        retrofit = RetrofitUtility.initializeRetrofit();


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
                    loginSubmit("admin","admin");
                }
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // action
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


    }

    public void loginSubmit(String userName,String password){
        LoginModel loginModel = new LoginModel(userName,password);

        JsonRestApi jsonRestApi = retrofit.create(JsonRestApi.class);
        Call<LoginResponse> hasil = jsonRestApi.getLogin(loginModel);

        hasil.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                try {
                    if (response.body().isSuccess()){
                        Toast.makeText(getApplicationContext(), "Login  " +
                                "Berhasil", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Login  " +
                                "Gagal", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "ini Exception " +
                            e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "ini trowable " +
                        t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    

}