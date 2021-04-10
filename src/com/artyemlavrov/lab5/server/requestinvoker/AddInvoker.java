package com.artyemlavrov.lab5.server.requestinvoker;

import com.artyemlavrov.lab5.request.AddRequest;
import com.artyemlavrov.lab5.response.singleelement.AddResponse;
import com.artyemlavrov.lab5.server.WorkersCollection;
import com.artyemlavrov.lab5.types.Worker;

public class AddInvoker implements RequestInvoker<AddRequest, AddResponse> {

    @Override
    public AddResponse invoke(WorkersCollection collection, AddRequest request) {
        Worker element = request.getElement();
        Worker newElement = collection.add(element);
        return new AddResponse(newElement);
    }
}
