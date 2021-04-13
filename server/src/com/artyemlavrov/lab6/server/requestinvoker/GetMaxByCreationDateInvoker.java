package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.request.GetMaxByCreationDateRequest;
import com.artyemlavrov.lab6.common.response.singleelement.GetMaxByCreationDateResponse;
import com.artyemlavrov.lab6.server.WorkersCollection;
import com.artyemlavrov.lab6.common.types.Worker;

public class GetMaxByCreationDateInvoker extends RequestInvoker<GetMaxByCreationDateRequest, GetMaxByCreationDateResponse> {
    @Override
    public GetMaxByCreationDateResponse invoke(WorkersCollection collection, GetMaxByCreationDateRequest request) {
        Worker element = collection.getMaxByCreationDate();
        return new GetMaxByCreationDateResponse(element);
    }

    @Override
    public Class<GetMaxByCreationDateRequest> getRequestClass() {
        return GetMaxByCreationDateRequest.class;
    }
}
