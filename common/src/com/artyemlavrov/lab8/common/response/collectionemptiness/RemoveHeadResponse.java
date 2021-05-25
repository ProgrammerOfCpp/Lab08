package com.artyemlavrov.lab8.common.response.collectionemptiness;

import com.artyemlavrov.lab8.common.types.Authentication;

public class RemoveHeadResponse extends CollectionEmptinessResponse {
    public RemoveHeadResponse(Authentication authentication, Boolean isCollectionEmpty) {
        super(authentication, isCollectionEmpty);
    }
}
