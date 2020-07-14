package com.example.androidlatihan.api;

import com.example.androidlatihan.model.Book;
import com.example.androidlatihan.model.BookResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BookApiService {
    @GET("api/buku")
    Call<List<Book>>getAllBook(@Header("Authorization")String token);
    @POST("api/buku")
    Call<Book> insertBook(@Header("Authorization") String token, @Body Book book);

}
