package com.artyemlavrov.lab5.server.requestinvoker;

import com.artyemlavrov.lab5.common.request.AddRequest;
import com.artyemlavrov.lab5.common.response.singleelement.AddResponse;
import com.artyemlavrov.lab5.server.WorkersCollection;
import com.artyemlavrov.lab5.common.types.Worker;

public class AddInvoker extends RequestInvoker<AddRequest, AddResponse> {

    @Override
    public AddResponse getResponse(WorkersCollection collection, AddRequest request) {
        Worker element = request.getElement();
        Worker newElement = collection.add(element);
        return new AddResponse(newElement);
    }

    @Override
    public Class<AddRequest> getRequestClass() {
        return AddRequest.class;
    }
}
