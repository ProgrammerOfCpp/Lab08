package com.artyemlavrov.lab5.server.requestinvoker;

import com.artyemlavrov.lab5.common.request.RemoveByIdRequest;
import com.artyemlavrov.lab5.common.response.elementexistence.RemoveByIdResponse;
import com.artyemlavrov.lab5.server.WorkersCollection;

public class RemoveByIdInvoker extends RequestInvoker<RemoveByIdRequest, RemoveByIdResponse> {

    @Override
    protected RemoveByIdResponse getResponse(WorkersCollection collection, RemoveByIdRequest request) {
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
