package com.example.androidlatihan.adapter;

import android.os.Parcel;
import android.os.Parcelable;

public class BookAdapter implements Parcelable {
    private int id;
    private String thumb,judul,penulis,penerbit,harga,tahun;

    public BookAdapter(){
    }

    protected BookAdapter(Parcel in){
        id = in.readInt();
        judul = in.readString();
        penulis = in.readString();
        penerbit = in.readString();
        harga = in.readString();
        tahun = in.readString();
        thumb = in.readString();
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public BookAdapter createFromParcel(Parcel in) {
            return new BookAdapter(in);
        }
        @Override
        public BookAdapter[] newArray(int size) {
            return new BookAdapter[size];
        }
    };

    @Override
    public int describeContents(){
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest,int flags){
        dest.writeInt(id);
        dest.writeString(judul);
        dest.writeString(penulis);
        dest.writeString(penerbit);
        dest.writeString(harga);
        dest.writeString(tahun);
        dest.writeString(thumb);
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String  getThumb() {
        return thumb;
    }

    public void setThumb(String  thumb) {
        this.thumb = thumb;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public static Creator getCREATOR() {
        return CREATOR;
    }
}
