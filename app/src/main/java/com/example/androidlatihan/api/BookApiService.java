package com.example.androidlatihan.api;

import com.example.androidlatihan.model.Book;
import com.example.androidlatihan.model.BookResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookApiService {
    //interface method get dari baseurl/api/buku
    @GET("api/buku")
    //memanggil list model buku dan getAllBook untuk dapatkan semua data dengan header token yang diambil dipanggil di activity HomeFragment
    Call<List<Book>>getAllBook(@Header("Authorization")String token);

    //interface method get dari baseurl/api/buku
    @POST("api/buku")
    //memanggil  model buku dan insertBook untuk method menginputkan semua data dari dengan header token yang diambil di activity AddFragment
    Call<Book> insertBook(@Header("Authorization") String token, @Body Book book);

    @DELETE("api/buku")
    Call<Book> deleteBook(@Header("Authorization") String token, @Query("id") int id);

    @GET("api/buku/byId/{id}")
    Call<BookResponse> getBookById(@Header("Authorization") String token, @Path("id")int id);

    @PUT("api/buku")
    Call<BookResponse> updateBook(@Header("Authorization")String token, @Body Book body);

}
