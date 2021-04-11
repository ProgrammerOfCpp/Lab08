package com.artyemlavrov.lab5.common.response.elementexistence;

import com.artyemlavrov.lab5.common.response.Response;

public class ElementExistenceResponse extends Response {
    private final Boolean hasElementExisted;

    public ElementExistenceResponse(Boolean hasElementExisted) {
        this.hasElementExisted = hasElementExisted;
    }

    public boolean hasElementExisted() {
        return hasElementExisted;
    }
}
