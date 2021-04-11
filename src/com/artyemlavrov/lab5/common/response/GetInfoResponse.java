package com.artyemlavrov.lab5.common.response;

import java.time.LocalDate;

public class GetInfoResponse extends Response {
    private final String collectionType;
    private final LocalDate initializationDate;
    private final Integer elementsCount;

    public GetInfoResponse(String collectionType, LocalDate initializationDate, Integer elementsCount) {
        this.collectionType = collectionType;
        this.initializationDate = initializationDate;
        this.elementsCount = elementsCount;
    }

    public LocalDate getInitializationDate() {
        return initializationDate;
    }

    public Integer getElementsCount() {
        return elementsCount;
    }

    public String getCollectionType() {
        return collectionType;
    }
}
