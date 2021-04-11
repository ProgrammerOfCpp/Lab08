package com.artyemlavrov.lab5.common.request;

public class RemoveByIdRequest extends Request {

    private final Integer id;

    public RemoveByIdRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
