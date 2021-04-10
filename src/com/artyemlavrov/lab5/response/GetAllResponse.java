package com.artyemlavrov.lab5.response;

import com.artyemlavrov.lab5.types.Worker;

import java.util.List;

public class GetAllResponse extends Response {
    private final List<Worker> elementsList;

    public GetAllResponse(List<Worker> elementsList) {
        this.elementsList = elementsList;
    }

    public List<Worker> getElementsList() {
        return elementsList;
    }
}
