package com.example.androidlatihan.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidlatihan.R;
import java.util.ArrayList;
import java.util.List;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberViewHolder> {
    private List<BookAdapter>bookAdaptersList;
    Dialog dialog;

    //method mendapatkan  list book
    public MemberListAdapter(){
        bookAdaptersList = new ArrayList<>();
    }
    private void add(BookAdapter item){
        bookAdaptersList.add(item);
        notifyItemInserted(bookAdaptersList.size()-1);
    }
    public void addAll(List<BookAdapter> bookAdaptersList){
        for (BookAdapter bookAdapter : bookAdaptersList){
            add(bookAdapter);
        }
    }
    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,parent,false);
        MemberViewHolder memberViewHolder = new MemberViewHolder(view);

        dialog = new Dialog(parent.getContext());
        dialog.setContentView(R.layout.edit_fragment);



        memberViewHolder.item_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText judul = dialog.findViewById(R.id.judulEdit);
                EditText penulis = dialog.findViewById(R.id.penulisEdit);
                EditText penerbit = dialog.findViewById(R.id.penerbitEdit);
                EditText harga = dialog.findViewById(R.id.hargaEdit);
                EditText tahun = dialog.findViewById(R.id.tahunEdit);
                ImageView thumb = dialog.findViewById(R.id.imageViewEdit);

                judul.setText(bookAdaptersList.get(memberViewHolder.getAdapterPosition()).getJudul());
                penulis.setText(bookAdaptersList.get(memberViewHolder.getAdapterPosition()).getPenulis());
                penerbit.setText(bookAdaptersList.get(memberViewHolder.getAdapterPosition()).getPenerbit());
                harga.setText(bookAdaptersList.get(memberViewHolder.getAdapterPosition()).getHarga());
                tahun.setText(bookAdaptersList.get(memberViewHolder.getAdapterPosition()).getTahun());
                thumb.setImageBitmap(getBitmap(bookAdaptersList.get(memberViewHolder.getAdapterPosition()).getThumb()));

                //Toast.makeText(,"id item list"+ bookAdaptersList.get(position).getId(),Toast.LENGTH_SHORT).show();
                dialog.show();



            }
        });


        return memberViewHolder;
    }

    @Override
    public void onBindViewHolder(MemberViewHolder holder, int position) {
        final BookAdapter bookAdapter = bookAdaptersList.get(position);

        Bitmap bitmap = getBitmap(bookAdapter.getThumb());

        holder.bookThumb.setImageBitmap(bitmap);
        holder.judul.setText(bookAdapter.getJudul());
        holder.penulis.setText(bookAdapter.getPenulis());


        holder.bookThumb.setOnClickListener(view ->{
            Log.e("TAG", "onBindViewHolder: " +bookAdaptersList.get(position).getId());

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

    //declarasi atribut yang dari item_List_Book
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
