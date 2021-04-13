package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.request.GetAllRequest;
import com.artyemlavrov.lab6.common.response.GetAllResponse;
import com.artyemlavrov.lab6.server.WorkersCollection;
import com.artyemlavrov.lab6.common.types.Worker;

import java.util.List;

public class GetAllInvoker extends RequestInvoker<GetAllRequest, GetAllResponse> {
    @Override
    public GetAllResponse invoke(WorkersCollection collection, GetAllRequest request) {
        List<Worker> elements = collection.getAll();
        return new GetAllResponse(elements);
    }

    @Override
    public Class<GetAllRequest> getRequestClass() {
        return GetAllRequest.class;
    }
}
