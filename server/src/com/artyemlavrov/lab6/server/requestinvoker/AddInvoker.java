package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.request.AddRequest;
import com.artyemlavrov.lab6.common.response.singleelement.AddResponse;
import com.artyemlavrov.lab6.server.WorkersCollection;
import com.artyemlavrov.lab6.common.types.Worker;

public class AddInvoker extends RequestInvoker<AddRequest, AddResponse> {

    @Override
    public AddResponse invoke(WorkersCollection collection, AddRequest request) {
        Worker element = request.getElement();
        Worker newElement = collection.add(element);
        return new AddResponse(newElement);
    }

    @Override
    public Class<AddRequest> getRequestClass() {
        return AddRequest.class;
    }
}
