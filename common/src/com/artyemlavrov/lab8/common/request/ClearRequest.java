package com.artyemlavrov.lab8.common.request;

import com.artyemlavrov.lab8.common.request.Request;
import com.artyemlavrov.lab8.common.types.Authentication;

public class ClearRequest extends Request {
    public ClearRequest(Authentication authentication) {
        super(authentication);
    }
}
