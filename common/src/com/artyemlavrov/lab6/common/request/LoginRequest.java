package com.artyemlavrov.lab6.common.request;

import com.artyemlavrov.lab6.common.types.Authentication;

public class LoginRequest extends Request {
    public LoginRequest(Authentication authentication) {
        super(authentication);
    }
}
