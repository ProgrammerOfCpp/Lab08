package com.artyemlavrov.lab5.server.requestinvoker;

public class UnknownRequestException extends Exception {

    @Override
    public String getMessage() {
        return "Неизвестный запрос.";
    }
}
