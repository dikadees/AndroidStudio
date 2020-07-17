package com.example.androidlatihan.utility;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//class retrofit
public class RetrofitUtility {

    //method untuk memanggil retofit
    public static Retrofit initializeRetrofit(){

        //membuat objek baru httplogging
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //objek tadi ambil bodinya
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //membuat objek baru untuk menangkap dari rest api dari http
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        //membuat objek retrofit baru
        Retrofit retrofit = new Retrofit.Builder()
                //dengan baseurl dari const baseurl
                .baseUrl(Const.BASE_URL)
                //dari base diconvert dengan converterGson
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
