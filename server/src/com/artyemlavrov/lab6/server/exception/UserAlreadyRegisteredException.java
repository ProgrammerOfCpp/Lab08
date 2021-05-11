package com.artyemlavrov.lab6.server.exception;

public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException() {
        super("Пользователь с данным именем уже зарегестрирован.");
    }
}
