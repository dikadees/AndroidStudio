package com.example.androidlatihan.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidlatihan.R;
import com.example.androidlatihan.activity.AppService;
import com.example.androidlatihan.activity.EditFragment;
import com.example.androidlatihan.activity.HomeFragment;
import com.example.androidlatihan.api.BookApiService;
import com.example.androidlatihan.model.Book;
import com.example.androidlatihan.utility.RetrofitUtility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberViewHolder> {

    private List<BookAdapter>bookAdaptersList;
    private Context mcontext;
    private HomeFragment fragment;

    //constructor untuk dapatkan resource dari parent(fragment)
    public MemberListAdapter(Context mcontext, HomeFragment fragment) {
        bookAdaptersList = new ArrayList<>();
        this.mcontext = mcontext;
        this.fragment = fragment;
    }

    private void add(BookAdapter item){
        bookAdaptersList.add(item);
        notifyItemInserted(bookAdaptersList.size()-1);
    }

    //
    public void addAll(List<BookAdapter> bookAdaptersList){
        for (BookAdapter bookAdapter : bookAdaptersList){
            add(bookAdapter);
        }
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,parent,false);
        MemberViewHolder memberViewHolder = new MemberViewHolder(view);
        return memberViewHolder;
    }


    @Override
    public void onBindViewHolder(MemberViewHolder holder, int position) {
        final BookAdapter bookAdapter = bookAdaptersList.get(position);

        Bitmap bitmap = getBitmap(bookAdapter.getThumb());

        holder.bookThumb.setImageBitmap(bitmap);
        holder.judul.setText(bookAdapter.getJudul());
        holder.penulis.setText(bookAdapter.getPenulis());

        //mendapatkan id
        int bukuId = bookAdaptersList.get(position).getId();

        holder.item_book.setOnClickListener(view -> {
            Log.e("TAG", "onBindViewHolder: " + bookAdapter.getId());

            //memanggil openFragmentView dan mengirimkan id
            fragment.openFragmentView(bukuId);
            //set id di appservice
            AppService.setId(bukuId);
        });

        holder.bookThumb.setOnClickListener(view ->{
            Log.e("TAG", "onBindViewHolder: " + bookAdapter.getId());

        });
        //klik lama judul
        holder.judul.setOnLongClickListener(view -> {
            Log.e("TAG", "long clik listener: ");
            return true;
        });
        //klik lama gambar
        holder.bookThumb.setOnLongClickListener(view -> {
            Log.e("TAG", "long click listener: ");
            return true;
        });
    }

    @Override
    public int getItemCount(){
        return bookAdaptersList.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView){
        super.onDetachedFromRecyclerView(recyclerView);
    }

    //method holder yang dibutuhkan recyclerview
    static class MemberViewHolder extends RecyclerView.ViewHolder{
        CardView item_book;
        ImageView bookThumb;
        TextView judul;
        TextView penulis;

        int id;
        public MemberViewHolder(View itemView){
            super(itemView);

            item_book = (CardView) itemView.findViewById(R.id.book_item_id);
            bookThumb = itemView.findViewById(R.id.thumb);
            judul = itemView.findViewById(R.id.judul);
            penulis = itemView.findViewById(R.id.penulis);
        }

    }

    // methode decode gambar dari base64 ke bitmap
    private Bitmap getBitmap(String base64String){
        byte[] decodedString = Base64.decode(base64String,Base64.DEFAULT);
        Bitmap decodeByte = BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);
        return decodeByte;
    }





}


//memunculkan dialog
//    Dialog dialog;


//        dialog = new Dialog(parent.getContext());
//        dialog.setContentView(R.layout.edit_fragment);
//
//        memberViewHolder.item_book.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Button delete = dialog.findViewById(R.id.btn_deleteBook);
//                EditText judul = dialog.findViewById(R.id.judulEdit);
//                EditText penulis = dialog.findViewById(R.id.penulisEdit);
//                EditText penerbit = dialog.findViewById(R.id.penerbitEdit);
//                EditText harga = dialog.findViewById(R.id.hargaEdit);
//                EditText tahun = dialog.findViewById(R.id.tahunEdit);
//                ImageView thumb = dialog.findViewById(R.id.imageViewEdit);
//
//
//                judul.setText(bookAdaptersList.get(memberViewHolder.getAdapterPosition()).getJudul());
//                penulis.setText(bookAdaptersList.get(memberViewHolder.getAdapterPosition()).getPenulis());
//                penerbit.setText(bookAdaptersList.get(memberViewHolder.getAdapterPosition()).getPenerbit());
//                harga.setText(bookAdaptersList.get(memberViewHolder.getAdapterPosition()).getHarga());
//                tahun.setText(bookAdaptersList.get(memberViewHolder.getAdapterPosition()).getTahun());
//                thumb.setImageBitmap(getBitmap(bookAdaptersList.get(memberViewHolder.getAdapterPosition()).getThumb()));
//
//
//
//                //Toast.makeText(,"id item list"+ bookAdaptersList.get(position).getId(),Toast.LENGTH_SHORT).show();
//                dialog.show();
//
//
//
//            }
//        });


// berpindah ke fragment
//            AppCompatActivity activity = (AppCompatActivity)view.getContext();
//            FragmentManager fragmentManager = activity.getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            //actifityFragment
//            EditFragment strCode = new EditFragment();
//            fragmentTransaction.replace(R.id.content, strCode, "edit fragment");
//            fragmentTransaction.commit();
//            Intent i = new Intent(view.getContext(),EditFragment.class);
//            Intent intent = new Intent(holder.itemView.getContext(),EditFragment.class);
//            Bundle b = new Bundle();
//
//            intent.putExtra("book_id", bookAdapter.getId()); //you can name the keys whatever you like
//            intent.putExtra("book_penulis", bookAdapter.getPenulis()); //note that all these values have to be primitive (i.e boolean, int, double, String, etc.)
//            intent.putExtra("book_penerbit", bookAdapter.getPenerbit());
//            intent.putExtra("book_tahun", bookAdapter.getTahun());
//            intent.putExtra("book_harga", bookAdapter.getHarga());
//            intent.putExtra("book_thumb", bookAdapter.getThumb());
//            intent.putExtras(b);
//