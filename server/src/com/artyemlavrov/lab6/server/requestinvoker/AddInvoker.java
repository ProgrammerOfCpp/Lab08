package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.request.AddRequest;
import com.artyemlavrov.lab6.common.response.singleelement.AddResponse;
import com.artyemlavrov.lab6.common.types.Worker;
import com.artyemlavrov.lab6.server.ServerApplication;
import com.artyemlavrov.lab6.server.database.CollectionProvider;

public class AddInvoker extends RequestInvoker<AddRequest> {

    public AddInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public AddResponse buildResponse(AddRequest request) {
        CollectionProvider collectionProvider = getCollectionProvider(request);
        Worker element = request.getElement();
        Worker newElement = collectionProvider.add(element);
        return new AddResponse(newElement);
    }

    @Override
    public Class<AddRequest> getRequestClass() {
        return AddRequest.class;
    }
}
