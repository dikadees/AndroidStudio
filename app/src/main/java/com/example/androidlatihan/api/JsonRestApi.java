package com.example.androidlatihan.api;

import com.example.androidlatihan.model.LoginModel;
import com.example.androidlatihan.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonRestApi {

    @POST("login")
    Call<LoginResponse> getLogin(@Body LoginModel loginModel);
}
