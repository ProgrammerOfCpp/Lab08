package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.request.RemoveByIdRequest;
import com.artyemlavrov.lab6.common.response.elementexistence.RemoveByIdResponse;
import com.artyemlavrov.lab6.server.WorkersCollection;

public class RemoveByIdInvoker extends RequestInvoker<RemoveByIdRequest, RemoveByIdResponse> {

    @Override
    public RemoveByIdResponse invoke(WorkersCollection collection, RemoveByIdRequest request) {
        Integer id = request.getId();
        if (collection.contains(id)) {
            collection.remove(id);
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
