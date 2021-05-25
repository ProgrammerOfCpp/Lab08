package com.artyemlavrov.lab8.server.requestinvoker;

import com.artyemlavrov.lab8.common.request.RemoveLowerRequest;
import com.artyemlavrov.lab8.common.response.RemoveLowerResponse;
import com.artyemlavrov.lab8.common.types.Worker;
import com.artyemlavrov.lab8.server.ServerApplication;
import com.artyemlavrov.lab8.server.database.CollectionProvider;

public class RemoveLowerInvoker extends RequestInvoker<RemoveLowerRequest> {

    public RemoveLowerInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public RemoveLowerResponse buildResponse(RemoveLowerRequest request) {
        Worker element = request.getElement();
        CollectionProvider collectionProvider = getCollectionProvider(request);
        if (collectionProvider.isEmpty()) {
            return new RemoveLowerResponse(request.getAuthentication(), false);
        } else {
            collectionProvider.removeLower(element);
            return new RemoveLowerResponse(request.getAuthentication(), true);
        }
    }

    @Override
    public Class<RemoveLowerRequest> getRequestClass() {
        return RemoveLowerRequest.class;
    }
}
