package com.artyemlavrov.lab8.common.request;

import com.artyemlavrov.lab8.common.request.Request;
import com.artyemlavrov.lab8.common.types.Authentication;

public class RemoveHeadRequest extends Request {
    public RemoveHeadRequest(Authentication authentication) {
        super(authentication);
    }
}
