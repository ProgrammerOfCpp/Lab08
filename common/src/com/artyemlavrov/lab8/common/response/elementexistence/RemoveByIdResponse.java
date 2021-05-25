package com.artyemlavrov.lab8.common.response.elementexistence;

import com.artyemlavrov.lab8.common.types.Authentication;

public class RemoveByIdResponse extends ElementExistenceResponse {

    public RemoveByIdResponse(Authentication authentication, Boolean hasElementExisted) {
        super(authentication, hasElementExisted);
    }
}
