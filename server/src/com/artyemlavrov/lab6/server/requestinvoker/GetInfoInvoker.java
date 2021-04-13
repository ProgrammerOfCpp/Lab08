package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.request.GetInfoRequest;
import com.artyemlavrov.lab6.common.response.GetInfoResponse;
import com.artyemlavrov.lab6.server.WorkersCollection;

public class GetInfoInvoker extends RequestInvoker<GetInfoRequest, GetInfoResponse> {
    @Override
    public GetInfoResponse invoke(WorkersCollection collection, GetInfoRequest request) {
        return new GetInfoResponse(
                collection.getType(),
                collection.getInitializationDate(),
                collection.getElementsCount()
        );
    }

    @Override
    public Class<GetInfoRequest> getRequestClass() {
        return GetInfoRequest.class;
    }
}
