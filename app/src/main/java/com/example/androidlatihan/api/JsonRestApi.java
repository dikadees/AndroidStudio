package com.example.androidlatihan.api;

import com.example.androidlatihan.model.LoginModel;
import com.example.androidlatihan.model.LoginResponse;
import com.example.androidlatihan.model.Result;
import com.example.androidlatihan.model.SignupModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonRestApi {

//    @GET("api/user")
//    Call<> get semua data (@Body )

    @POST("login")
    Call<LoginResponse> getLogin(@Body LoginModel loginModel);

    @POST("api/user")
    Call<Result> getSignUp(@Body SignupModel signupModel);
}
