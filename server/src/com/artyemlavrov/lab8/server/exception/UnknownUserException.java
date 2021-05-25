package com.artyemlavrov.lab8.server.exception;

public class UnknownUserException extends RuntimeException {
    public UnknownUserException() {
        super("Пользователь с данным именем отсутствует.");
    }
}
