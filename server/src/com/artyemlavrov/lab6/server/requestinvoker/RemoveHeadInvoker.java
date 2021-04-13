package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.request.RemoveHeadRequest;
import com.artyemlavrov.lab6.common.response.collectionemptiness.RemoveHeadResponse;
import com.artyemlavrov.lab6.server.WorkersCollection;

public class RemoveHeadInvoker extends RequestInvoker<RemoveHeadRequest, RemoveHeadResponse> {

    @Override
    public RemoveHeadResponse invoke(WorkersCollection collection, RemoveHeadRequest request) {
        if (collection.isEmpty()) {
            return new RemoveHeadResponse(true);
        } else {
            collection.removeHead();
            return new RemoveHeadResponse(false);
        }
    }

    @Override
    public Class<RemoveHeadRequest> getRequestClass() {
        return RemoveHeadRequest.class;
    }
}
