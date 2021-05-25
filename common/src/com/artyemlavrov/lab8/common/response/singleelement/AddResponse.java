package com.artyemlavrov.lab8.common.response.singleelement;

import com.artyemlavrov.lab8.common.types.Authentication;
import com.artyemlavrov.lab8.common.types.Worker;

public class AddResponse extends SingleElementResponse {

    public AddResponse(Authentication authentication, Worker element) {
        super(authentication, element);
    }
}
