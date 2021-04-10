package com.artyemlavrov.lab5.server.requestinvoker;

import com.artyemlavrov.lab5.request.ClearRequest;
import com.artyemlavrov.lab5.response.collectionemptiness.ClearResponse;
import com.artyemlavrov.lab5.server.WorkersCollection;

public class ClearInvoker implements RequestInvoker<ClearRequest, ClearResponse> {
    @Override
    public ClearResponse invoke(WorkersCollection collection, ClearRequest request) {
        if (collection.isEmpty()) {
            return new ClearResponse(true);
        } else {
            collection.clear();
            return new ClearResponse(false);
        }
    }
}
