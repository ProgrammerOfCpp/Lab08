package com.artyemlavrov.lab8.server.database;

public class User {
    private final int id;
    private final String username;
    private final String password;
    private final int color;

    public User(int id, String username, String password, int color) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
