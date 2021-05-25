package com.artyemlavrov.lab8.server.requestinvoker;

import com.artyemlavrov.lab8.common.request.GetStatusDescendingRequest;
import com.artyemlavrov.lab8.common.response.GetStatusDescendingResponse;
import com.artyemlavrov.lab8.common.types.Status;
import com.artyemlavrov.lab8.server.ServerApplication;
import com.artyemlavrov.lab8.server.database.CollectionProvider;

import java.util.List;

public class GetStatusDescendingInvoker extends RequestInvoker<GetStatusDescendingRequest> {

    public GetStatusDescendingInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public GetStatusDescendingResponse buildResponse(GetStatusDescendingRequest request) {
        CollectionProvider collectionProvider = getCollectionProvider(request);
        List<Status> statusList = collectionProvider.getStatusListDescending();
        return new GetStatusDescendingResponse(request.getAuthentication(), statusList);
    }

    @Override
    public Class<GetStatusDescendingRequest> getRequestClass() {
        return GetStatusDescendingRequest.class;
    }
}
