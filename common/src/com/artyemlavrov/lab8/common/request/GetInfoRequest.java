package com.artyemlavrov.lab8.common.request;

import com.artyemlavrov.lab8.common.types.Authentication;

public class GetInfoRequest extends Request {
    public GetInfoRequest(Authentication authentication) {
        super(authentication);
    }
}
