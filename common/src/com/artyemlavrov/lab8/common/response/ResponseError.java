package com.artyemlavrov.lab8.common.response;

import com.artyemlavrov.lab8.common.types.Authentication;

public class ResponseError extends Response {
    private final String message;

    public ResponseError(Authentication authentication, String message) {
        super(authentication);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
