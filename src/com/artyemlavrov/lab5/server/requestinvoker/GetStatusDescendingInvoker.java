package com.artyemlavrov.lab5.server.requestinvoker;

import com.artyemlavrov.lab5.request.GetStatusDescendingRequest;
import com.artyemlavrov.lab5.response.GetStatusDescendingResponse;
import com.artyemlavrov.lab5.server.WorkersCollection;
import com.artyemlavrov.lab5.types.Status;

import java.util.List;

public class GetStatusDescendingInvoker implements RequestInvoker<GetStatusDescendingRequest, GetStatusDescendingResponse> {
    @Override
    public GetStatusDescendingResponse invoke(WorkersCollection collection, GetStatusDescendingRequest request) {
        List<Status> statusList = collection.getStatusListDescending();
        return new GetStatusDescendingResponse(statusList);
    }
}
