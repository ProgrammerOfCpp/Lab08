package com.artyemlavrov.lab8.server.requestinvoker;

import com.artyemlavrov.lab8.common.request.GetAllRequest;
import com.artyemlavrov.lab8.common.response.GetAllResponse;
import com.artyemlavrov.lab8.common.types.Worker;
import com.artyemlavrov.lab8.server.ServerApplication;
import com.artyemlavrov.lab8.server.database.CollectionProvider;

import java.util.List;

public class GetAllInvoker extends RequestInvoker<GetAllRequest> {

    public GetAllInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public GetAllResponse buildResponse(GetAllRequest request) {
        CollectionProvider collectionProvider = getCollectionProvider(request);
        List<Worker> elements = collectionProvider.getAll();
        return new GetAllResponse(request.getAuthentication(), elements);
    }

    @Override
    public Class<GetAllRequest> getRequestClass() {
        return GetAllRequest.class;
    }
}
