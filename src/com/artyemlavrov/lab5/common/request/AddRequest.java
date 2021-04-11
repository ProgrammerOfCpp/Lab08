package com.artyemlavrov.lab5.common.request;

import com.artyemlavrov.lab5.common.types.Worker;

public class AddRequest extends Request {
    private final Worker element;

    public AddRequest(Worker element) {
        this.element = element;
    }

    public Worker getElement() {
        return element;
    }
}
