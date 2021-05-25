package com.artyemlavrov.lab8.common.response.singleelement;

import com.artyemlavrov.lab8.common.response.singleelement.SingleElementResponse;
import com.artyemlavrov.lab8.common.types.Authentication;
import com.artyemlavrov.lab8.common.types.Worker;

public class GetMaxByCreationDateResponse extends SingleElementResponse {
    public GetMaxByCreationDateResponse(Authentication authentication, Worker element) {
        super(authentication, element);
    }
}
