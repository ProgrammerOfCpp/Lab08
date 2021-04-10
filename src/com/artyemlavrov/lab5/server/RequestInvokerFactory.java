package com.artyemlavrov.lab5.server;

import com.artyemlavrov.lab5.request.AddRequest;
import com.artyemlavrov.lab5.request.Request;
import com.artyemlavrov.lab5.server.requestinvoker.AddInvoker;
import com.artyemlavrov.lab5.server.requestinvoker.RequestInvoker;

public class RequestInvokerFactory {
    public RequestInvoker instantiate(Request request) {
        if (request instanceof AddRequest) {
            return new AddInvoker();
        }
        return null;
    }
}
