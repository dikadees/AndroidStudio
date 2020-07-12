package com.example.androidlatihan.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidlatihan.R;
import java.util.ArrayList;
import java.util.List;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberViewHolder> {
    private List<BookAdapter>bookAdaptersList;

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
        return memberViewHolder;
    }

    @Override
    public void onBindViewHolder(MemberViewHolder holder, int position) {
        final BookAdapter bookAdapter = bookAdaptersList.get(position);

        holder.bookThumb.setImageResource(bookAdapter.getThumb());
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
    static class MemberViewHolder extends RecyclerView.ViewHolder{
        ImageView bookThumb;
        TextView judul;
        TextView penulis;
        int id;
        public MemberViewHolder(View itemView){
            super(itemView);

            bookThumb = itemView.findViewById(R.id.thumb);
            judul = itemView.findViewById(R.id.judul);
            penulis = itemView.findViewById(R.id.penulis);
        }
    }



}
