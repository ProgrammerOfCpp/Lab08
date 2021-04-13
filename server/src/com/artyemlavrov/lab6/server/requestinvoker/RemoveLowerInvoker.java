package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.request.RemoveLowerRequest;
import com.artyemlavrov.lab6.common.response.RemoveLowerResponse;
import com.artyemlavrov.lab6.common.types.Worker;
import com.artyemlavrov.lab6.server.WorkersCollection;

public class RemoveLowerInvoker extends RequestInvoker<RemoveLowerRequest, RemoveLowerResponse> {

    @Override
    public RemoveLowerResponse invoke(WorkersCollection collection, RemoveLowerRequest request) {
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
