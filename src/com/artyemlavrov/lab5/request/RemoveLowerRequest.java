package com.artyemlavrov.lab5.request;

import com.artyemlavrov.lab5.types.Worker;

public class RemoveLowerRequest extends Request {
    private final Worker element;

    public RemoveLowerRequest(Worker element) {
        this.element = element;
    }

    public Worker getElement() {
        return element;
    }
}
