package com.artyemlavrov.lab8.common.response.singleelement;

import com.artyemlavrov.lab8.common.response.Response;
import com.artyemlavrov.lab8.common.types.Authentication;
import com.artyemlavrov.lab8.common.types.Worker;

public abstract class SingleElementResponse extends Response {
    private final Worker element;

    public SingleElementResponse(Authentication authentication, Worker element) {
        super(authentication);
        this.element = element;
    }

    public Worker getElement() {
        return element;
    }
}
