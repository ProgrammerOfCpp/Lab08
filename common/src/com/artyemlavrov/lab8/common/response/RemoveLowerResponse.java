package com.artyemlavrov.lab8.common.response;

import com.artyemlavrov.lab8.common.response.Response;
import com.artyemlavrov.lab8.common.types.Authentication;

public class RemoveLowerResponse extends Response {
    private final Boolean haveLowerElementsExisted;

    public RemoveLowerResponse(Authentication authentication, Boolean haveLowerElementsExisted) {
        super(authentication);
        this.haveLowerElementsExisted = haveLowerElementsExisted;
    }

    public boolean haveLowerElementsExisted() {
        return haveLowerElementsExisted;
    }
}
