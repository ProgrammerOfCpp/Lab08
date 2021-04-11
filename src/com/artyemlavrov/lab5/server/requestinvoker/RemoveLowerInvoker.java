package com.artyemlavrov.lab5.server.requestinvoker;

import com.artyemlavrov.lab5.common.request.RemoveLowerRequest;
import com.artyemlavrov.lab5.common.response.RemoveLowerResponse;
import com.artyemlavrov.lab5.common.types.Worker;
import com.artyemlavrov.lab5.server.WorkersCollection;

public class RemoveLowerInvoker extends RequestInvoker<RemoveLowerRequest, RemoveLowerResponse> {

    @Override
    protected RemoveLowerResponse getResponse(WorkersCollection collection, RemoveLowerRequest request) {
        Worker element = request.getElement();
        if (collection.isEmpty()) {
            return new RemoveLowerResponse(false);
        } else {
            collection.removeLower(element);
            return new RemoveLowerResponse(true);
        }
    }

    @Override
    public Class<RemoveLowerRequest> getRequestClass() {
        return RemoveLowerRequest.class;
    }
}
