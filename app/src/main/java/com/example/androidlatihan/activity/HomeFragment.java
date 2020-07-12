package com.example.androidlatihan.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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

    private void initRecyclerView(){
        listMember = view.findViewById(R.id.listMember);
        linearLayoutManager = new LinearLayoutManager(context);
        memberListAdapter = new MemberListAdapter();
        listMember.setLayoutManager(linearLayoutManager);
        listMember.setAdapter(memberListAdapter);

    }

    private void initRetrofit(){
        retrofit = RetrofitUtility.initializeRetrofit();
    }

    private void getAllBookData(){
        BookApiService apiService = retrofit.create(BookApiService.class);
        Call<List<Book>> result = apiService.getAllBook(AppService.getToken());
         result.enqueue(new Callback<List<Book>>() {
             @Override
             public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                 addData(response.body());
             }

             @Override
             public void onFailure(Call<List<Book>> call, Throwable t) {
                 t.printStackTrace();

             }
         });
    }


    private void addData (List<Book>data){
        List<BookAdapter>bookAdapterList = new ArrayList<>();
        BookAdapter bookAdapter;

        for (Book book : data){
            Log.e("TAG", "addData: " + book.getJudul() );
            bookAdapter = new BookAdapter();
            bookAdapter.setId(book.getId());
            bookAdapter.setJudul(book.getJudul());
            bookAdapter.setPenulis(book.getPenulis());
            bookAdapter.setThumb(R.drawable.icon_twitter);
            bookAdapterList.add(bookAdapter);
        }

        memberListAdapter.addAll(bookAdapterList);
    }










}
