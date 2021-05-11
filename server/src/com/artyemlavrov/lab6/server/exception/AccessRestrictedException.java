package com.artyemlavrov.lab6.server.exception;

public class AccessRestrictedException extends RuntimeException {
    public AccessRestrictedException() {
        super("Пользователь не обладает необходимыми правами для выполнения данной операции.");
    }
}
