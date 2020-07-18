package com.example.androidlatihan.model;

public class BookResponse {
    private String message;
    private boolean success;
    private Book record;

    public BookResponse(String message, boolean success, Book record) {
        this.message = message;
        this.success = success;
        this.record = record;
    }

    public Book getRecord() {
        return record;
    }

    public void setRecord(Book record) {
        this.record = record;
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
