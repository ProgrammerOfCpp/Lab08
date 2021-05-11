package com.artyemlavrov.lab6.common.request;

import com.artyemlavrov.lab6.common.types.Authentication;

public class GetInfoRequest extends Request {
    public GetInfoRequest(Authentication authentication) {
        super(authentication);
    }
}
