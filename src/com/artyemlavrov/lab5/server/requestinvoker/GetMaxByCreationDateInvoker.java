package com.artyemlavrov.lab5.server.requestinvoker;

import com.artyemlavrov.lab5.common.request.GetMaxByCreationDateRequest;
import com.artyemlavrov.lab5.common.response.singleelement.GetMaxByCreationDateResponse;
import com.artyemlavrov.lab5.server.WorkersCollection;
import com.artyemlavrov.lab5.common.types.Worker;

public class GetMaxByCreationDateInvoker extends RequestInvoker<GetMaxByCreationDateRequest, GetMaxByCreationDateResponse> {
    @Override
    public GetMaxByCreationDateResponse getResponse(WorkersCollection collection, GetMaxByCreationDateRequest request) {
        Worker element = collection.getMaxByCreationDate();
        return new GetMaxByCreationDateResponse(element);
    }

    @Override
    public Class<GetMaxByCreationDateRequest> getRequestClass() {
        return GetMaxByCreationDateRequest.class;
    }
}
