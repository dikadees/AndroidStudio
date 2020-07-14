package com.example.androidlatihan.model;

public class Book {
    private int id,harga,userid;
    private String judul,penerbit,penulis,thumb,tahun;
    private  boolean success;
    private Record record;
    private String token;

    public Book(int id, int harga, int userid, String judul, String penerbit, String penulis, String thumb, String tahun, boolean success, Record record, String token) {
        this.id = id;
        this.harga = harga;
        this.userid = userid;
        this.judul = judul;
        this.penerbit = penerbit;
        this.penulis = penulis;
        this.thumb = thumb;
        this.tahun = tahun;
        this.success = success;
        this.record = record;
        this.token = token;
    }

    public Book() {
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
