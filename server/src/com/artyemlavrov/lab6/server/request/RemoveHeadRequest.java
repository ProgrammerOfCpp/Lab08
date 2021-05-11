package com.artyemlavrov.lab6.server.request;

import com.artyemlavrov.lab6.common.request.Request;
import com.artyemlavrov.lab6.common.types.Authentication;

public class RemoveHeadRequest extends Request {
    public RemoveHeadRequest(Authentication authentication) {
        super(authentication);
    }
}
