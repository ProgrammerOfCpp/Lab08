package com.artyemlavrov.lab6.common.response;

public class RemoveLowerResponse extends Response {
    private final Boolean haveLowerElementsExisted;

    public RemoveLowerResponse(Boolean haveLowerElementsExisted) {
        this.haveLowerElementsExisted = haveLowerElementsExisted;
    }

    public boolean haveLowerElementsExisted() {
        return haveLowerElementsExisted;
    }
}
