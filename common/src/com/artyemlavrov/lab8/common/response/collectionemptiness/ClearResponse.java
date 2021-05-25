package com.artyemlavrov.lab8.common.response.collectionemptiness;

import com.artyemlavrov.lab8.common.types.Authentication;

public class ClearResponse extends CollectionEmptinessResponse {
    public ClearResponse(Authentication authentication, Boolean isCollectionEmpty) {
        super(authentication, isCollectionEmpty);
    }
}
