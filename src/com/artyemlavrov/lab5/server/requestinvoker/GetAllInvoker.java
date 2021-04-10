package com.artyemlavrov.lab5.server.requestinvoker;

import com.artyemlavrov.lab5.request.GetAllRequest;
import com.artyemlavrov.lab5.response.GetAllResponse;
import com.artyemlavrov.lab5.server.WorkersCollection;
import com.artyemlavrov.lab5.types.Worker;

import java.util.List;

public class GetAllInvoker implements RequestInvoker<GetAllRequest, GetAllResponse> {
    @Override
    public GetAllResponse invoke(WorkersCollection collection, GetAllRequest request) {
        List<Worker> elements = collection.getAll();
        return new GetAllResponse(elements);
    }
}
