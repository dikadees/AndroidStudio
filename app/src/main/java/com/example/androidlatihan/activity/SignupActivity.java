package com.example.androidlatihan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.androidlatihan.R;
import com.example.androidlatihan.api.JsonRestApi;
import com.example.androidlatihan.model.Result;
import com.example.androidlatihan.model.SignupModel;
import com.example.androidlatihan.utility.RetrofitUtility;
import com.example.androidlatihan.utility.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText username, name, email, password;
    RadioButton rb1, rb2;
    Spinner role;
    Button btndaftar;
    private boolean rbAktif;
    private AwesomeValidation awesomeValidation;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        username = findViewById(R.id.username);
        name = findViewById(R.id.fullname);
        email =findViewById(R.id.email);
        password = findViewById(R.id.password);
        role = findViewById(R.id.spinnerRole);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        btndaftar = findViewById(R.id.btn_signup);


        //askpermission
        Utility.askPermission(this);
        retrofit = RetrofitUtility.initializeRetrofit();


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(SignupActivity.this,R.id.password, RegexTemplate.NOT_EMPTY,R.string.passwordError);
        awesomeValidation.addValidation(this, R.id.username, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.usernameError);
        awesomeValidation.addValidation(this, R.id.fullname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.fullNameError);
        awesomeValidation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS, R.string.emailError);
        awesomeValidation.addValidation(SignupActivity.this,R.id.active,RegexTemplate.NOT_EMPTY,R.string.activeError);
        awesomeValidation.addValidation(SignupActivity.this,R.id.spinnerRole,RegexTemplate.NOT_EMPTY,R.string.roleError);



        btndaftar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(awesomeValidation.validate()){
                List<String> hasilRole = new ArrayList<>();
                hasilRole.add(role.getSelectedItem().toString());

                SignUpSubmit(name.getText().toString(), email.getText().toString(), username.getText().toString(), password.getText().toString(), hasilRole, rbAktif);
                Toast.makeText(getApplicationContext(), "Sing Up  " +
                        "Berhasil", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Sing Up  " +
                        "Gagal", Toast.LENGTH_SHORT).show();
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



    public void SignUpSubmit(String name, String email, String username, String password, List<String>role, boolean radioGroup){
        SignupModel signupModel = new SignupModel(name, email, username, password, role, radioGroup);

        JsonRestApi jsonRestApi = retrofit.create(JsonRestApi.class);
        Call<Result> data =jsonRestApi.getSignUp(signupModel);

        data.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                try {
                    if (response.body().isSuccess()){
                        Toast.makeText(getApplicationContext(), "Sign Up  " +
                                "Berhasil", Toast.LENGTH_SHORT).show();
                        Log.e("TAG","Sign Up Succes" + response.body().toString());
                        Intent LoginIntent = new Intent(SignupActivity.this,MainActivity.class);
                        startActivity(LoginIntent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Sign Up  Gagal " +
                                response, Toast.LENGTH_SHORT).show();
                        Log.e("TAG","Sign Up Gagal" + response.body().toString());
                    }
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "ini Exception " +
                            e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "ini trowable " +
                        t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }



}