package com.example.androidlatihan.api;

import com.example.androidlatihan.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface BookApiService {
    @GET("api/buku")
    Call<List<Book>>getAllBook(@Header("Authorization")String token);
}
