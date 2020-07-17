package com.example.androidlatihan.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlatihan.R;
import com.example.androidlatihan.adapter.BookAdapter;
import com.example.androidlatihan.adapter.MemberListAdapter;
import com.example.androidlatihan.api.BookApiService;
import com.example.androidlatihan.model.Book;
import com.example.androidlatihan.utility.RetrofitUtility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    private View view;
    private Retrofit retrofit;
    private String TAG = "home fragment";
    private RecyclerView listMember;
    private LinearLayoutManager linearLayoutManager;
    private MemberListAdapter memberListAdapter;
    protected Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout,container,false);
        initRetrofit();
        getAllBookData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
    }

    //
    private void initRecyclerView(){
        listMember = view.findViewById(R.id.listMember);
        linearLayoutManager = new LinearLayoutManager(context);
        memberListAdapter = new MemberListAdapter();
        listMember.setLayoutManager(linearLayoutManager);
        listMember.setAdapter(memberListAdapter);

    }

    //memanggil retrofit
    private void initRetrofit(){
        retrofit = RetrofitUtility.initializeRetrofit();
    }

    private void getAllBookData(){

        //memanggil retrofit dari BookApiService
        BookApiService apiService = retrofit.create(BookApiService.class);
        //memanggil List book yg dikirimkan BookApiService untuk mendapatkan semua data dan mengenerate token
        Call<List<Book>> result = apiService.getAllBook(AppService.getToken());
        //var result menampung antrian dan membuat objek dari balasan List book yang di panggil
         result.enqueue(new Callback<List<Book>>() {
             @Override
             //memanggil response dari list book
             public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                 //menampilkan data dari response http
                 //dikirimkan di addData
                 addData(response.body());
             }

             @Override
             //jika gagal
             public void onFailure(Call<List<Book>> call, Throwable t) {
                 //menampilkan eror dan cetak di Stacktrace
                 t.printStackTrace();

             }
         });
    }


    //menerima data dan ditampung disini
    private void addData (List<Book>data){
        //membuat objek dali list book diatas dibuat objekaraylist
        List<BookAdapter>bookAdapterList = new ArrayList<>();
        BookAdapter bookAdapter;

        //mengulang data yang diterima
        for (Book book : data){
            Log.e("TAG", "addData: " + book.getJudul() );
            bookAdapter = new BookAdapter();
            bookAdapter.setId(book.getId());
            bookAdapter.setJudul(book.getJudul());
            bookAdapter.setPenulis(book.getPenulis());
            bookAdapter.setPenerbit(book.getPenerbit());
            bookAdapter.setTahun(book.getTahun());
            bookAdapter.setHarga(String.valueOf(book.getHarga()));
            bookAdapter.setThumb(book.getThumb());
            bookAdapterList.add(bookAdapter);
        }

        memberListAdapter.addAll(bookAdapterList);
    }










}
