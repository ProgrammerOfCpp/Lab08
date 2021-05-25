package com.artyemlavrov.lab8.common.response;

import com.artyemlavrov.lab8.common.types.Authentication;

public class GetColorResponse extends Response {
    private final Integer color;

    public GetColorResponse(Authentication authentication, Integer color) {
        super(authentication);
        this.color = color;
    }

    public Integer getColor() {
        return color;
    }
}
