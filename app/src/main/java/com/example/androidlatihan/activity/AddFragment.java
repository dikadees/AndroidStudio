package com.example.androidlatihan.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import com.example.androidlatihan.utility.Utility;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddFragment extends Fragment {
    private View view;
    private EditText judul,penulis,penerbit,harga,tahun;
    private String base64Image;
    private String TAG = "Add Fragment";
    private ImageView imageView;
    private Button btn_image,btn_add;
    private Retrofit retrofit;
    private static final int PICK_IMAGE=1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.add_fragment, container, false);

        initRetrofit();

        judul = view.findViewById(R.id.judul);
        penerbit = view.findViewById(R.id.penerbit);
        penulis = view.findViewById(R.id.penulis);
        harga = view.findViewById(R.id.harga);
        tahun = view.findViewById(R.id.tahun);
        imageView = view.findViewById(R.id.imageView);
        btn_add = view.findViewById(R.id.btn_addBook);
        btn_image = view.findViewById(R.id.btn_imageView);

        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Add Image",Toast.LENGTH_SHORT).show();
                imageChooser();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validatejudul() | !validatepenulis() | !validatepenerbit() | !validatetahun() | !validateharga()) {
                    Toast.makeText(getActivity(), "Masukkan Data", Toast.LENGTH_SHORT).show();
                }else {
                    insertBook(
                            judul.getText().toString(),
                            penulis.getText().toString(),
                            penerbit.getText().toString(),
                            harga.getText().toString(),
                            tahun.getText().toString(),
                            base64Image
                    );
                }
            }
        });

        return view;
    }

    private void imageChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE);
    }
    private String encodeImage(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,60,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b,Base64.DEFAULT);
        return encImage;
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode == PICK_IMAGE ){
            Uri uri = data.getData();
            InputStream imageStream;
            String encodedImage = "";
            try{
                imageView.setImageURI(uri);
                imageStream = getContext().getContentResolver().openInputStream(uri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                encodedImage = encodeImage(selectedImage);
            }catch (FileNotFoundException e ){
                e.printStackTrace();
            }
            base64Image = encodedImage;
        }
    }

    private void insertBook(String judul, String penulis, String penerbit, String harga, String tahun, String base64Image) {
        Book book = new Book();
        book.setHarga(Integer.valueOf(harga));
        book.setJudul(judul);
        book.setPenulis(penulis);
        book.setPenerbit(penerbit);
        book.setTahun(tahun);
        book.setThumb(base64Image);

        BookApiService bookApiService = retrofit.create(BookApiService.class);
        Call<Book> result = bookApiService.insertBook(AppService.getToken(), book);
        result.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.body().isSuccess()) {
                    Log.e("TAG", "Input Success");
                    Toast.makeText(getActivity(),"Add succes",Toast.LENGTH_SHORT).show();
                    openHomeFragment();
                } else {
                    Log.e("TAG", "Input Gagal" + response.body().getThumb());
                    Toast.makeText(getActivity(),"Add gagal" + response.message(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
    private void initRetrofit() {
        retrofit = RetrofitUtility.initializeRetrofit();
    }

    private boolean validatejudul() {

        String judulinput = judul.getText().toString().trim();

        if (judulinput.isEmpty()) {
            judul.setError("Field can't be empty");
            return false;
        } else {
            judul.setError(null);
            return true;
        }


    }

    private boolean validatepenerbit() {

        String penerbitinput = penerbit.getText().toString().trim();

        if (penerbitinput.isEmpty()) {
            penerbit.setError("Field can't be empty");
            return false;
        } else {
            penerbit.setError(null);
            return true;
        }


    }

    private boolean validatepenulis() {

        String penulisinput = penulis.getText().toString().trim();

        if (penulisinput.isEmpty()) {
            penulis.setError("Field can't be empty");
            return false;
        } else {
            penulis.setError(null);
            return true;
        }


    }

    private boolean validatetahun() {

        String tahuninput = tahun.getText().toString().trim();

        if (tahuninput.isEmpty()) {
            tahun.setError("Field can't be empty");
            return false;
        }  else {
            tahun.setError(null);
            return true;
        }


    }

    private boolean validateharga() {

        String hargainput = harga.getText().toString().trim();

        if (hargainput.isEmpty()) {
            harga.setError("Field can't be empty");
            return false;
        } else {
            harga.setError(null);
            return true;
        }


    }

    private void openHomeFragment(){

        HomeFragment strCode = new HomeFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, strCode, "home fragment");
        fragmentTransaction.commit();

    }



}