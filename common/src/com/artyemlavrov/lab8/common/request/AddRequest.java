package com.artyemlavrov.lab8.common.request;

import com.artyemlavrov.lab8.common.types.Authentication;
import com.artyemlavrov.lab8.common.types.Worker;

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
