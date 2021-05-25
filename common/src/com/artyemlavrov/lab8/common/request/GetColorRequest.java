package com.artyemlavrov.lab8.common.request;

import com.artyemlavrov.lab8.common.types.Authentication;

import java.util.List;

public class GetColorRequest extends Request {
    private final Integer elementId;

    public GetColorRequest(Authentication authentication, Integer elementId) {
        super(authentication);
        this.elementId = elementId;
    }

    public Integer getElementId() {
        return elementId;
    }

    @Override
    public Authentication getAuthentication() {
        return super.getAuthentication();
    }
}
