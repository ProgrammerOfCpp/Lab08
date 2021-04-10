package com.artyemlavrov.lab5.server;

import com.artyemlavrov.lab5.request.Request;
import com.artyemlavrov.lab5.response.Response;
import com.artyemlavrov.lab5.server.requestinvoker.RequestInvoker;

public class Server {
    private final WorkersCollection collection = new WorkersCollection();

    public Response processRequest(Request request) {
        RequestInvokerFactory requestInvokerFactory = new RequestInvokerFactory();
        RequestInvoker requestInvoker = requestInvokerFactory.instantiate(request);
        return requestInvoker.invoke(collection, request);
    }
}
