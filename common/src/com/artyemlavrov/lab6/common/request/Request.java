package com.artyemlavrov.lab6.common.request;

import com.artyemlavrov.lab6.common.types.Authentication;

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
