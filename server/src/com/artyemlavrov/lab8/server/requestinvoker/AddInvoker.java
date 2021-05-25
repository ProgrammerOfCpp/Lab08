package com.artyemlavrov.lab8.server.requestinvoker;

import com.artyemlavrov.lab8.common.request.AddRequest;
import com.artyemlavrov.lab8.common.response.singleelement.AddResponse;
import com.artyemlavrov.lab8.common.types.Worker;
import com.artyemlavrov.lab8.server.ServerApplication;
import com.artyemlavrov.lab8.server.database.CollectionProvider;

public class AddInvoker extends RequestInvoker<AddRequest> {

    public AddInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public AddResponse buildResponse(AddRequest request) {
        CollectionProvider collectionProvider = getCollectionProvider(request);
        Worker element = request.getElement();
        Worker newElement = collectionProvider.add(element);
        return new AddResponse(request.getAuthentication(), newElement);
    }

    @Override
    public Class<AddRequest> getRequestClass() {
        return AddRequest.class;
    }
}
