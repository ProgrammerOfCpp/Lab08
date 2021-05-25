package com.artyemlavrov.lab8.server.requestinvoker;

import com.artyemlavrov.lab8.common.request.ClearRequest;
import com.artyemlavrov.lab8.common.response.collectionemptiness.ClearResponse;
import com.artyemlavrov.lab8.server.ServerApplication;
import com.artyemlavrov.lab8.server.database.CollectionProvider;

public class ClearInvoker extends RequestInvoker<ClearRequest> {

    public ClearInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public ClearResponse buildResponse(ClearRequest request) {
        CollectionProvider collectionProvider = getCollectionProvider(request);
        if (collectionProvider.isEmpty()) {
            return new ClearResponse(request.getAuthentication(), true);
        } else {
            collectionProvider.clear();
            return new ClearResponse(request.getAuthentication(), false);
        }
    }

    @Override
    public Class<ClearRequest> getRequestClass() {
        return ClearRequest.class;
    }
}
