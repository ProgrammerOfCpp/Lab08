package com.artyemlavrov.lab8.common.request;

import com.artyemlavrov.lab8.common.types.Authentication;

public class RegisterRequest extends Request {
    public RegisterRequest(Authentication authentication) {
        super(authentication);
    }
}
