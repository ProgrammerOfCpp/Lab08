package com.artyemlavrov.lab6.common.interpreter;

public class UnknownCommandException extends Exception {

    @Override
    public String getMessage() {
        return "Команда с указанным именем не найдена.";
    }
}
