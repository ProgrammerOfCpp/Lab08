package com.artyemlavrov.lab8.common.response.collectionemptiness;

import com.artyemlavrov.lab8.common.response.Response;
import com.artyemlavrov.lab8.common.types.Authentication;

public class CollectionEmptinessResponse extends Response {
    private final Boolean isCollectionEmpty;

    public CollectionEmptinessResponse(Authentication authentication, Boolean isCollectionEmpty) {
        super(authentication);
        this.isCollectionEmpty = isCollectionEmpty;
    }

    public boolean isCollectionEmpty() {
        return this.isCollectionEmpty;
    }
}
