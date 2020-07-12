package com.example.androidlatihan.model;

public class Book {
    private int id,harga,userid;
    private String judul,penerbit,penulis,thumb;

    public Book(int id, int harga, int userid, String judul, String penerbit, String penulis,String thumb) {
        this.id = id;
        this.harga = harga;
        this.userid = userid;
        this.judul = judul;
        this.penerbit = penerbit;
        this.penulis = penulis;
        this.thumb = thumb;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
