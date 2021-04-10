package com.artyemlavrov.lab5.response.collectionemptiness;

import com.artyemlavrov.lab5.response.Response;

public class CollectionEmptinessResponse extends Response {
    private final Boolean isCollectionEmpty;

    public CollectionEmptinessResponse(Boolean isCollectionEmpty) {
        this.isCollectionEmpty = isCollectionEmpty;
    }

    public boolean isCollectionEmpty() {
        return this.isCollectionEmpty;
    }
}
