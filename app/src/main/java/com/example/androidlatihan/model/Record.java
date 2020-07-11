package com.example.androidlatihan.model;

import java.util.ArrayList;
import java.util.List;

public class Record {

    private Integer id;
    private String username,email,name;
    private List<String> roles = new ArrayList<>();
    boolean active;

    public Record(int id, String username, String email, String name, List<String> roles, boolean active) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.roles = roles;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public boolean isActive() {
        return active;
    }
}
