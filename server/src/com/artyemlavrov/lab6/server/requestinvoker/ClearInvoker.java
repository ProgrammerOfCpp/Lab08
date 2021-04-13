package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.request.ClearRequest;
import com.artyemlavrov.lab6.common.response.collectionemptiness.ClearResponse;
import com.artyemlavrov.lab6.server.WorkersCollection;

public class ClearInvoker extends RequestInvoker<ClearRequest, ClearResponse> {
    @Override
    public ClearResponse invoke(WorkersCollection collection, ClearRequest request) {
        if (collection.isEmpty()) {
            return new ClearResponse(true);
        } else {
            collection.clear();
            return new ClearResponse(false);
        }
    }

    @Override
    public Class<ClearRequest> getRequestClass() {
        return ClearRequest.class;
    }
}
