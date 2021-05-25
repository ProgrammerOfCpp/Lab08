package com.artyemlavrov.lab8.common.exception;

public class UnknownCommandException extends Exception {

    @Override
    public String getMessage() {
        return "Команда с указанным именем не найдена.";
    }
}
