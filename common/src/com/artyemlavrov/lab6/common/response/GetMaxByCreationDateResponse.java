package com.artyemlavrov.lab6.common.response;

import com.artyemlavrov.lab6.common.response.singleelement.SingleElementResponse;
import com.artyemlavrov.lab6.common.types.Worker;

public class GetMaxByCreationDateResponse extends SingleElementResponse {
    public GetMaxByCreationDateResponse(Worker element) {
        super(element);
    }
}
