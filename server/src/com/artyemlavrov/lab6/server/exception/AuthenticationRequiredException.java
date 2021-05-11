package com.artyemlavrov.lab6.server.exception;

public class AuthenticationRequiredException extends RuntimeException {

    public AuthenticationRequiredException() {
        super("Требуется аутентификация.");
    }
}
