package com.example.androidlatihan.model;

public class LoginResponse {
    private boolean success;
    private String token;
    private Record record;

    public LoginResponse(boolean success, String token, Record record) {
        this.success = success;
        this.token = token;
        this.record = record;
    }

    public LoginResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public Record getRecord() {
        return record;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
