package com.artyemlavrov.lab5.common.response.singleelement;

import com.artyemlavrov.lab5.common.response.Response;
import com.artyemlavrov.lab5.common.types.Worker;

public abstract class SingleElementResponse extends Response {
    private final Worker element;

    public SingleElementResponse(Worker element) {
        this.element = element;
    }

    public Worker getElement() {
        return element;
    }
}
