package com.artyemlavrov.lab6.server.database;

import com.artyemlavrov.lab6.common.types.Authentication;

public class AuthenticationProvider {
    private final Database database;

    public AuthenticationProvider(Database database) {
        this.database = database;
    }

    public void register(Authentication authentication) {
        database.createUser(authentication);
    }

    public void login(Authentication authentication) {
        database.readUser(authentication);
    }
}
