package com.artyemlavrov.lab8.server.requestinvoker;

import com.artyemlavrov.lab8.common.request.RemoveHeadRequest;
import com.artyemlavrov.lab8.common.response.collectionemptiness.RemoveHeadResponse;
import com.artyemlavrov.lab8.server.ServerApplication;
import com.artyemlavrov.lab8.server.database.CollectionProvider;

public class RemoveHeadInvoker extends RequestInvoker<RemoveHeadRequest> {

    public RemoveHeadInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public RemoveHeadResponse buildResponse(RemoveHeadRequest request) {
        CollectionProvider collectionProvider = getCollectionProvider(request);
        if (collectionProvider.isEmpty()) {
            return new RemoveHeadResponse(request.getAuthentication(), true);
        } else {
            collectionProvider.removeHead();
            return new RemoveHeadResponse(request.getAuthentication(), false);
        }
    }

    @Override
    public Class<RemoveHeadRequest> getRequestClass() {
        return RemoveHeadRequest.class;
    }
}
