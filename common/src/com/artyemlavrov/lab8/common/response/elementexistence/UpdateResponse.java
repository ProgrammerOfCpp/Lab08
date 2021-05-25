package com.artyemlavrov.lab8.common.response.elementexistence;

import com.artyemlavrov.lab8.common.types.Authentication;

public class UpdateResponse extends ElementExistenceResponse {
    public UpdateResponse(Authentication authentication, Boolean hasElementExisted) {
        super(authentication, hasElementExisted);
    }
}
