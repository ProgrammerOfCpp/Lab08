package com.artyemlavrov.lab6.common.response.singleelement;

import com.artyemlavrov.lab6.common.response.Response;
import com.artyemlavrov.lab6.common.types.Worker;

public abstract class SingleElementResponse extends Response {
    private final Worker element;

    public SingleElementResponse(Worker element) {
        this.element = element;
    }

    public Worker getElement() {
        return element;
    }
}
