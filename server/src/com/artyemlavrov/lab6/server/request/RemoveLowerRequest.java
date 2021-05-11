package com.artyemlavrov.lab6.server.request;

import com.artyemlavrov.lab6.common.request.Request;
import com.artyemlavrov.lab6.common.types.Authentication;
import com.artyemlavrov.lab6.common.types.Worker;

public class RemoveLowerRequest extends Request {
    private final Worker element;

    public RemoveLowerRequest(Authentication authentication, Worker element) {
        super(authentication);
        this.element = element;
    }

    public Worker getElement() {
        return element;
    }
}
