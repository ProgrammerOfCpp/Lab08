package com.artyemlavrov.lab8.common.request;

import com.artyemlavrov.lab8.common.types.Authentication;

public class LoginRequest extends Request {
    public LoginRequest(Authentication authentication) {
        super(authentication);
    }
}
