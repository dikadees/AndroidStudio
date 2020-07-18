package com.example.androidlatihan.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidlatihan.R;
import com.example.androidlatihan.api.BookApiService;
import com.example.androidlatihan.model.Book;
import com.example.androidlatihan.model.BookResponse;
import com.example.androidlatihan.utility.RetrofitUtility;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class EditFragment extends Fragment {

    private View view;
    private Retrofit retrofit;
    private String TAG = "editDelete fragment";
    private EditText edit_judul,edit_penulis,edit_penerbit,edit_tahun,edit_harga;
    private Button btnUpdate,btnDelete,btnAddImage;
    private ImageView imageView;
    private static final int PICK_IMAGE = 1;
    private String base64Image = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.edit_fragment, container, false);

        getData();
        initView();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
    private void initView(){
        edit_judul = view.findViewById(R.id.judulEdit);
        edit_penulis = view.findViewById(R.id.penulisEdit);
        edit_penerbit = view.findViewById(R.id.penerbitEdit);
        edit_tahun = view.findViewById(R.id.tahunEdit);
        edit_harga = view.findViewById(R.id.hargaEdit);
        imageView = view.findViewById(R.id.imageViewEdit);
        btnDelete = view.findViewById(R.id.btn_deleteBook);
        btnUpdate = view.findViewById(R.id.btn_editBook);
        btnAddImage = view.findViewById(R.id.btn_imageViewEdit);

        btnAddImage.setOnClickListener(view1 -> {

            imageChooser();

        });

        btnUpdate.setOnClickListener(view1 -> {
            sendData(edit_judul.getText().toString()
                    ,edit_penulis.getText().toString()
                    ,edit_penerbit.getText().toString()
                    ,edit_tahun.getText().toString()
                    ,edit_harga.getText().toString()
                    ,base64Image);
        });

        btnDelete.setOnClickListener(view1 -> {
            deleteBook();
        });
    }
    private void imageChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE);
    }
    private void getData(){

        retrofit = RetrofitUtility.initializeRetrofit();
        BookApiService apiService = retrofit.create(BookApiService.class);
        Call<BookResponse> result = apiService.getBookById(AppService.getToken(), AppService.getId());
        result.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.body().isSuccess()){


                    String judul = response.body().getRecord().getJudul() != null ? response.body().getRecord().getJudul() : "";
                    String penulis = response.body().getRecord().getPenulis() != null ? response.body().getRecord().getPenulis() : "";
                    String penerbit = response.body().getRecord().getPenerbit() != null ? response.body().getRecord().getPenerbit() : "";
                    String harga = response.body().getRecord().getHarga() > 0 ? String.valueOf(response.body().getRecord().getHarga()) : "";
                    String tahun = response.body().getRecord().getTahun() != null ? response.body().getRecord().getTahun() : "";



                    Log.e("TAG", "judul: " + judul );
                    Log.e("TAG", "penullis: " + penulis );
                    Log.e("TAG", "penerbit: " + penerbit );
                    Log.e("TAG", "tahun: " + tahun );
                    Log.e("TAG", "harga: " + harga );
                    Log.e("TAG", "image: " + base64Image );

                    edit_judul.setText(judul);
                    edit_penulis.setText(penulis);
                    edit_penerbit.setText(penerbit);
                    edit_tahun.setText(tahun);
                    edit_harga.setText(harga);
                    setImageThumb(response.body().getRecord().getThumb());
                }else {
                    Log.e(TAG, "error : " + response.message());
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                t.printStackTrace();
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }

    private Bitmap setImageThumb(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(decodedByte);
        return decodedByte;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
            InputStream imageStream;
            String encodedImage = "";

            imageView.getLayoutParams().height = 400;
            imageView.getLayoutParams().width = 300;
            try {
                imageStream = getContext().getContentResolver().openInputStream(uri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                encodedImage = encodedImage + encodeImage(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            base64Image = encodedImage;
        }
    };

    private void deleteBook(){
        BookApiService apiService = retrofit.create(BookApiService.class);
        Call<Book> result = apiService.deleteBook(AppService.getToken(), AppService.getId());
        result.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Log.e(TAG, "onResponse: " + response.body().toString());
                if (response.body().isSuccess()){
                    Toast.makeText(getContext(),"Succes Delete",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(),"Gagal Delete",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                t.printStackTrace();
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encImage;
    }



    private void sendData(String judul, String penulis, String penerbit, String tahun, String harga,String base64Image){
        Book book = new Book();
        book.setHarga(Integer.valueOf(harga));
        book.setId(AppService.getId());
        book.setJudul(judul);
        book.setPenulis(penulis);
        book.setPenerbit(penerbit);
        book.setTahun(tahun);
        book.setThumb(base64Image);

        Log.e(TAG, "sendData: " + book.toString());

        BookApiService apiService = retrofit.create(BookApiService.class);
        Call<BookResponse> result = apiService.updateBook(AppService.getToken(), book);
        result.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                Log.e(TAG, "onResponse: " + response.body().toString());
                if (response.body().isSuccess()) {
                    Toast.makeText(getContext(),"Success Edit Data",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"Gagal Edit Data",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(),"failure" + t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


}