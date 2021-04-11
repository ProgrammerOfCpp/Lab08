package com.artyemlavrov.lab5.server.requestinvoker;

import com.artyemlavrov.lab5.common.request.GetStatusDescendingRequest;
import com.artyemlavrov.lab5.common.response.GetStatusDescendingResponse;
import com.artyemlavrov.lab5.server.WorkersCollection;
import com.artyemlavrov.lab5.common.types.Status;

import java.util.List;

public class GetStatusDescendingInvoker extends RequestInvoker<GetStatusDescendingRequest, GetStatusDescendingResponse> {
    @Override
    public GetStatusDescendingResponse getResponse(WorkersCollection collection, GetStatusDescendingRequest request) {
        List<Status> statusList = collection.getStatusListDescending();
        return new GetStatusDescendingResponse(statusList);
    }

    @Override
    public Class<GetStatusDescendingRequest> getRequestClass() {
        return GetStatusDescendingRequest.class;
    }
}
