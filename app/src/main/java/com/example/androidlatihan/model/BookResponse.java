package com.example.androidlatihan.model;

public class BookResponse {
    private String message;
    private boolean success;


    public BookResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public BookResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
