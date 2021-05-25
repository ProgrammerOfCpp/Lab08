package com.artyemlavrov.lab8.server.exception;

public class AccessRestrictedException extends RuntimeException {
    public AccessRestrictedException() {
        super("Пользователь не обладает необходимыми правами для выполнения данной операции.");
    }
}
