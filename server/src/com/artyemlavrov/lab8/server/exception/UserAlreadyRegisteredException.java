package com.artyemlavrov.lab8.server.exception;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException() {
        super("Пользователь с данным именем уже зарегестрирован.");
    }
}
