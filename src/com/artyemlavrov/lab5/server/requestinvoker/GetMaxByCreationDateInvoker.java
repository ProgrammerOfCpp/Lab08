package com.artyemlavrov.lab5.server.requestinvoker;

import com.artyemlavrov.lab5.request.GetMaxByCreationDateRequest;
import com.artyemlavrov.lab5.response.singleelement.GetMaxByCreationDateResponse;
import com.artyemlavrov.lab5.server.WorkersCollection;
import com.artyemlavrov.lab5.types.Worker;

public class GetMaxByCreationDateInvoker implements RequestInvoker<GetMaxByCreationDateRequest, GetMaxByCreationDateResponse> {
    @Override
    public GetMaxByCreationDateResponse invoke(WorkersCollection collection, GetMaxByCreationDateRequest request) {
        Worker element = collection.getMaxByCreationDate();
        return new GetMaxByCreationDateResponse(element);
    }
}
