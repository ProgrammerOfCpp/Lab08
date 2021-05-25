package com.artyemlavrov.lab8.common.response;

import com.artyemlavrov.lab8.common.types.Authentication;

import java.io.Serializable;

public abstract class Response implements Serializable {
    private final Authentication authentication;

    protected Response(Authentication authentication) {
        this.authentication = authentication;
    }

    public Authentication getAuthentication() {
        return authentication;
    }
}
