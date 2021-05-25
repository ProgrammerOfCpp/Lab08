package com.artyemlavrov.lab8.common.response;

import com.artyemlavrov.lab8.common.types.Authentication;
import com.artyemlavrov.lab8.common.types.Worker;

import java.util.List;

public class GetAllResponse extends Response {
    private final List<Worker> elementsList;

    public GetAllResponse(Authentication authentication, List<Worker> elementsList) {
        super(authentication);
        this.elementsList = elementsList;
    }

    public List<Worker> getElementsList() {
        return elementsList;
    }
}
