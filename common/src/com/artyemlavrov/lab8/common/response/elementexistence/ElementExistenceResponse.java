package com.artyemlavrov.lab8.common.response.elementexistence;

import com.artyemlavrov.lab8.common.response.Response;
import com.artyemlavrov.lab8.common.types.Authentication;

public class ElementExistenceResponse extends Response {
    private final Boolean hasElementExisted;

    public ElementExistenceResponse(Authentication authentication, Boolean hasElementExisted) {
        super(authentication);
        this.hasElementExisted = hasElementExisted;
    }

    public boolean hasElementExisted() {
        return hasElementExisted;
    }
}
