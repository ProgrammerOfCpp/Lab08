package com.artyemlavrov.lab8.server.exception;

public class WrongCredentialsException extends RuntimeException {
    public WrongCredentialsException() {
        super("Неверный пароль");
    }
}
