package com.artyemlavrov.lab5.server.requestinvoker;

import com.artyemlavrov.lab5.common.request.UpdateRequest;
import com.artyemlavrov.lab5.common.response.elementexistence.UpdateResponse;
import com.artyemlavrov.lab5.common.types.Worker;
import com.artyemlavrov.lab5.server.WorkersCollection;

public class UpdateInvoker extends RequestInvoker<UpdateRequest, UpdateResponse> {

    @Override
    protected UpdateResponse getResponse(WorkersCollection collection, UpdateRequest request) {
        Integer id = request.getId();
        Worker value = request.getValue();

        if (collection.contains(id)) {
            collection.update(id, value);
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
