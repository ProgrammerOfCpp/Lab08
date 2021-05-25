package com.artyemlavrov.lab8.server.requestinvoker;

import com.artyemlavrov.lab8.common.request.RemoveByIdRequest;
import com.artyemlavrov.lab8.common.response.elementexistence.RemoveByIdResponse;
import com.artyemlavrov.lab8.server.ServerApplication;
import com.artyemlavrov.lab8.server.database.CollectionProvider;

public class RemoveByIdInvoker extends RequestInvoker<RemoveByIdRequest> {

    public RemoveByIdInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public RemoveByIdResponse buildResponse(RemoveByIdRequest request) {
        Integer id = request.getId();
        CollectionProvider collectionProvider = getCollectionProvider(request);
        if (collectionProvider.contains(id)) {
            collectionProvider.remove(id);
            return new RemoveByIdResponse(request.getAuthentication(), true);
        } else {
            return new RemoveByIdResponse(request.getAuthentication(), false);
        }
    }

    @Override
    public Class<RemoveByIdRequest> getRequestClass() {
        return RemoveByIdRequest.class;
    }
}
