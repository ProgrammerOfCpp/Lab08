package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.request.UpdateRequest;
import com.artyemlavrov.lab6.common.response.elementexistence.UpdateResponse;
import com.artyemlavrov.lab6.common.types.Worker;
import com.artyemlavrov.lab6.server.WorkersCollection;

public class UpdateInvoker extends RequestInvoker<UpdateRequest, UpdateResponse> {

    @Override
    public UpdateResponse invoke(WorkersCollection collection, UpdateRequest request) {
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
