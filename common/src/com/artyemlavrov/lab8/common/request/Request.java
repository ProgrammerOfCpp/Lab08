package com.artyemlavrov.lab8.common.request;

import com.artyemlavrov.lab8.common.types.Authentication;

import java.io.Serializable;

public abstract class Request implements Serializable {
    private final Authentication authentication;

    protected Request(Authentication authentication) {
        this.authentication = authentication;
    }

    public Authentication getAuthentication() {
        return authentication;
    }
}
