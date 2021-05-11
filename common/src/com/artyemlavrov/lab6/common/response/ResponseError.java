package com.artyemlavrov.lab6.common.response;

public class ResponseError extends Response {
    private final String message;

    public ResponseError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
