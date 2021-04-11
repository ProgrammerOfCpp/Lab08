package com.artyemlavrov.lab5.server.requestinvoker;

import com.artyemlavrov.lab5.common.request.GetAllRequest;
import com.artyemlavrov.lab5.common.response.GetAllResponse;
import com.artyemlavrov.lab5.server.WorkersCollection;
import com.artyemlavrov.lab5.common.types.Worker;

import java.util.List;

public class GetAllInvoker extends RequestInvoker<GetAllRequest, GetAllResponse> {
    @Override
    public GetAllResponse getResponse(WorkersCollection collection, GetAllRequest request) {
        List<Worker> elements = collection.getAll();
        return new GetAllResponse(elements);
    }

    @Override
    public Class<GetAllRequest> getRequestClass() {
        return GetAllRequest.class;
    }
}
