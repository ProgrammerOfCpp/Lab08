package com.artyemlavrov.lab6.common.request;

import com.artyemlavrov.lab6.common.types.Authentication;

public class RegisterRequest extends Request {
    public RegisterRequest(Authentication authentication) {
        super(authentication);
    }
}
