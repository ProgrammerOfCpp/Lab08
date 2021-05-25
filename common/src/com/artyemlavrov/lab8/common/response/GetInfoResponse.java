package com.artyemlavrov.lab8.common.response;

import com.artyemlavrov.lab8.common.types.Authentication;

import java.time.LocalDate;

public class GetInfoResponse extends Response {
    private final String collectionType;
    private final LocalDate initializationDate;
    private final Integer elementsCount;

    public GetInfoResponse(Authentication authentication, String collectionType, LocalDate initializationDate, Integer elementsCount) {
        super(authentication);
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
