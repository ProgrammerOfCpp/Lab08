package com.artyemlavrov.lab5.server.requestinvoker;

import com.artyemlavrov.lab5.common.request.GetInfoRequest;
import com.artyemlavrov.lab5.common.response.GetInfoResponse;
import com.artyemlavrov.lab5.server.WorkersCollection;

public class GetInfoInvoker implements RequestInvoker<GetInfoRequest, GetInfoResponse> {
    @Override
    public GetInfoResponse invoke(WorkersCollection collection, GetInfoRequest request) {
        return new GetInfoResponse(
                collection.getType(),
                collection.getInitializationDate(),
                collection.getElementsCount()
        );
    }
}
