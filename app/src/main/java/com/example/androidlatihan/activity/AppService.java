package com.example.androidlatihan.activity;

public class AppService {
    private static String token;
    private static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        AppService.id = id;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        AppService.token = token;
    }
}
