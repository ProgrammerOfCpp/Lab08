package com.artyemlavrov.lab6.common.request;

import com.artyemlavrov.lab6.common.types.Authentication;

public class RemoveByIdRequest extends Request {

    private final Integer id;

    public RemoveByIdRequest(Authentication authentication, Integer id) {
        super(authentication);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
