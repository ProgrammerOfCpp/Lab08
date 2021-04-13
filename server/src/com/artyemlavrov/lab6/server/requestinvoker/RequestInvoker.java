package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.request.Request;
import com.artyemlavrov.lab6.common.response.Response;
import com.artyemlavrov.lab6.server.WorkersCollection;

public abstract class RequestInvoker<RequestType extends Request, ResponseType extends Response> {

    public abstract ResponseType invoke(WorkersCollection collection, RequestType request);

    public abstract Class<RequestType> getRequestClass();
}
