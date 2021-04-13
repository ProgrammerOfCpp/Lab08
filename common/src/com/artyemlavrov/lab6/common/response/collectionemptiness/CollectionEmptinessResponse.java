package com.artyemlavrov.lab6.common.response.collectionemptiness;

import com.artyemlavrov.lab6.common.response.Response;

public class CollectionEmptinessResponse extends Response {
    private final Boolean isCollectionEmpty;

    public CollectionEmptinessResponse(Boolean isCollectionEmpty) {
        this.isCollectionEmpty = isCollectionEmpty;
    }

    public boolean isCollectionEmpty() {
        return this.isCollectionEmpty;
    }
}
