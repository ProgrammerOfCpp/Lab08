package com.artyemlavrov.lab6.server.exception;

public class WrongCredentialsException extends RuntimeException {
    public WrongCredentialsException() {
        super("Неверный пароль");
    }
}
