package com.artyemlavrov.lab8.server.requestinvoker;

import com.artyemlavrov.lab8.common.request.GetMaxByCreationDateRequest;
import com.artyemlavrov.lab8.common.response.singleelement.GetMaxByCreationDateResponse;
import com.artyemlavrov.lab8.common.types.Worker;
import com.artyemlavrov.lab8.server.ServerApplication;
import com.artyemlavrov.lab8.server.database.CollectionProvider;

public class GetMaxByCreationDateInvoker extends RequestInvoker<GetMaxByCreationDateRequest> {

    public GetMaxByCreationDateInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public GetMaxByCreationDateResponse buildResponse(GetMaxByCreationDateRequest request) {
        CollectionProvider collectionProvider = getCollectionProvider(request);
        Worker element = collectionProvider.getMaxByCreationDate();
        return new GetMaxByCreationDateResponse(request.getAuthentication(), element);
    }

    @Override
    public Class<GetMaxByCreationDateRequest> getRequestClass() {
        return GetMaxByCreationDateRequest.class;
    }
}
