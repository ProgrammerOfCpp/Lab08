package com.artyemlavrov.lab5.server;

import com.artyemlavrov.lab5.common.request.AddRequest;
import com.artyemlavrov.lab5.common.request.Request;
import com.artyemlavrov.lab5.common.response.Response;
import com.artyemlavrov.lab5.server.requestinvoker.AddInvoker;
import com.artyemlavrov.lab5.server.requestinvoker.RequestInvoker;

public class RequestInvokerFactory {
    public RequestInvoker<? extends Request, ? extends Response> instantiate(Request request) {
        if (request instanceof AddRequest) {
            return new AddInvoker();
        }
        return null;
    }
}
