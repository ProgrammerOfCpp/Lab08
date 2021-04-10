package com.artyemlavrov.lab5.request;

import com.artyemlavrov.lab5.types.Worker;

public class AddRequest extends Request {
    private final Worker element;

    public AddRequest(Worker element) {
        this.element = element;
    }

    public Worker getElement() {
        return element;
    }
}
