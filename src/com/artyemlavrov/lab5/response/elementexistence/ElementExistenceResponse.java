package com.artyemlavrov.lab5.response.elementexistence;

import com.artyemlavrov.lab5.response.Response;

public class ElementExistenceResponse extends Response {
    private final Boolean hasElementExisted;

    public ElementExistenceResponse(Boolean hasElementExisted) {
        this.hasElementExisted = hasElementExisted;
    }

    public boolean hasElementExisted() {
        return hasElementExisted;
    }
}
