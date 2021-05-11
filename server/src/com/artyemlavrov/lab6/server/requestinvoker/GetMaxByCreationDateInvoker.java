package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.request.GetMaxByCreationDateRequest;
import com.artyemlavrov.lab6.common.response.GetMaxByCreationDateResponse;
import com.artyemlavrov.lab6.common.types.Worker;
import com.artyemlavrov.lab6.server.ServerApplication;
import com.artyemlavrov.lab6.server.database.CollectionProvider;

public class GetMaxByCreationDateInvoker extends RequestInvoker<GetMaxByCreationDateRequest> {

    public GetMaxByCreationDateInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public GetMaxByCreationDateResponse buildResponse(GetMaxByCreationDateRequest request) {
        CollectionProvider collectionProvider = getCollectionProvider(request);
        Worker element = collectionProvider.getMaxByCreationDate();
        return new GetMaxByCreationDateResponse(element);
    }

    @Override
    public Class<GetMaxByCreationDateRequest> getRequestClass() {
        return GetMaxByCreationDateRequest.class;
    }
}
