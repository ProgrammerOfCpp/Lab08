package com.artyemlavrov.lab8.server.exception;

public class AuthenticationRequiredException extends RuntimeException {

    public AuthenticationRequiredException() {
        super("Требуется аутентификация.");
    }
}
