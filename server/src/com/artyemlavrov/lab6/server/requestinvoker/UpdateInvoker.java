package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.request.UpdateRequest;
import com.artyemlavrov.lab6.common.response.elementexistence.UpdateResponse;
import com.artyemlavrov.lab6.common.types.Worker;
import com.artyemlavrov.lab6.server.ServerApplication;
import com.artyemlavrov.lab6.server.database.CollectionProvider;

public class UpdateInvoker extends RequestInvoker<UpdateRequest> {

    public UpdateInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public UpdateResponse buildResponse(UpdateRequest request) {
        Integer id = request.getId();
        Worker value = request.getValue();
        CollectionProvider collectionProvider = getCollectionProvider(request);
        if (collectionProvider.contains(id)) {
            collectionProvider.update(id, value);
            return new UpdateResponse(true);
        } else {
            return new UpdateResponse(false);
        }
    }

    @Override
    public Class<UpdateRequest> getRequestClass() {
        return UpdateRequest.class;
    }
}
