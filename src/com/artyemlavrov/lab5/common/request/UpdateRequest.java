package com.artyemlavrov.lab5.common.request;

import com.artyemlavrov.lab5.common.types.Worker;

public class UpdateRequest extends Request {
    private final Integer id;
    private final Worker value;

    public UpdateRequest(Integer id, Worker value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public Worker getValue() {
        return value;
    }
}
