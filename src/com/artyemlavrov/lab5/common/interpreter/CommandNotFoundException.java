package com.artyemlavrov.lab5.common.interpreter;

public class CommandNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "Команда с указанным именем не найдена.";
    }
}
