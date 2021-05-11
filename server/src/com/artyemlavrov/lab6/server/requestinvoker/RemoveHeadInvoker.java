package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.server.request.RemoveHeadRequest;
import com.artyemlavrov.lab6.server.response.collectionemptiness.RemoveHeadResponse;
import com.artyemlavrov.lab6.server.ServerApplication;
import com.artyemlavrov.lab6.server.database.CollectionProvider;

public class RemoveHeadInvoker extends RequestInvoker<RemoveHeadRequest> {

    public RemoveHeadInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public RemoveHeadResponse buildResponse(RemoveHeadRequest request) {
        CollectionProvider collectionProvider = getCollectionProvider(request);
        if (collectionProvider.isEmpty()) {
            return new RemoveHeadResponse(true);
        } else {
            collectionProvider.removeHead();
            return new RemoveHeadResponse(false);
        }
    }

    @Override
    public Class<RemoveHeadRequest> getRequestClass() {
        return RemoveHeadRequest.class;
    }
}
