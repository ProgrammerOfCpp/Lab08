package com.artyemlavrov.lab8.server.requestinvoker;

import com.artyemlavrov.lab8.common.request.GetInfoRequest;
import com.artyemlavrov.lab8.common.response.GetInfoResponse;
import com.artyemlavrov.lab8.server.ServerApplication;
import com.artyemlavrov.lab8.server.database.CollectionProvider;

public class GetInfoInvoker extends RequestInvoker<GetInfoRequest> {

    public GetInfoInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public GetInfoResponse buildResponse(GetInfoRequest request) {
        CollectionProvider collectionProvider = getCollectionProvider(request);
        return new GetInfoResponse(
                request.getAuthentication(),
                collectionProvider.getType(),
                collectionProvider.getInitializationDate(),
                collectionProvider.getElementsCount()
        );
    }

    @Override
    public Class<GetInfoRequest> getRequestClass() {
        return GetInfoRequest.class;
    }
}
