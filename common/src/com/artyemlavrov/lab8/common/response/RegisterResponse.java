package com.artyemlavrov.lab8.common.response;

import com.artyemlavrov.lab8.common.types.Authentication;

public class RegisterResponse extends Response {

    public RegisterResponse(Authentication authentication) {
        super(authentication);
    }
}
