package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.request.RemoveByIdRequest;
import com.artyemlavrov.lab6.common.response.elementexistence.RemoveByIdResponse;
import com.artyemlavrov.lab6.server.ServerApplication;
import com.artyemlavrov.lab6.server.database.CollectionProvider;

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
            return new RemoveByIdResponse(true);
        } else {
            return new RemoveByIdResponse(false);
        }
    }

    @Override
    public Class<RemoveByIdRequest> getRequestClass() {
        return RemoveByIdRequest.class;
    }
}
