package com.example.androidlatihan.model;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private String message;
    private Record record;
    private boolean success;

    public Result(String message, Record record, boolean success) {
        this.message = message;
        this.record = record;
        this.success = success;
    }

    public Result() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
