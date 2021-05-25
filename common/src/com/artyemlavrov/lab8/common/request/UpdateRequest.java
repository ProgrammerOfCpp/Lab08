package com.artyemlavrov.lab8.common.request;

import com.artyemlavrov.lab8.common.types.Authentication;
import com.artyemlavrov.lab8.common.types.Worker;

public class UpdateRequest extends Request {
    private final Integer id;
    private final Worker value;

    public UpdateRequest(Authentication authentication, Integer id, Worker value) {
        super(authentication);
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
