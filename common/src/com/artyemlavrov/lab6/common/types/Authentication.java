package com.artyemlavrov.lab6.common.types;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authentication implements Serializable {
    private final String username;
    private final String password;


    public Authentication(String username, String password) {
        this.username = username;
        byte[] bytes = new byte[0];
        try {
            bytes = password.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        this.password = new String(bytes);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static final Authentication empty = new Authentication("", "");
}
