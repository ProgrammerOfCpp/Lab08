package com.artyemlavrov.lab6.common.request;

import com.artyemlavrov.lab6.common.types.Authentication;
import com.artyemlavrov.lab6.common.types.Worker;

public class AddRequest extends Request {
    private final Worker element;

    public AddRequest(Authentication authentication, Worker element) {
        super(authentication);
        this.element = element;
    }

    public Worker getElement() {
        return element;
    }
}
