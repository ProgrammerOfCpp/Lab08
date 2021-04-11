package com.artyemlavrov.lab5.server.requestinvoker;

import com.artyemlavrov.lab5.common.request.Request;
import com.artyemlavrov.lab5.common.response.Response;
import com.artyemlavrov.lab5.server.WorkersCollection;

public abstract class RequestInvoker<RequestType extends Request, ResponseType extends Response> {

    protected abstract ResponseType getResponse(WorkersCollection collection, RequestType request);

    public abstract Class<RequestType> getRequestClass();

    public ResponseType invoke(WorkersCollection collection, Request requestRaw) throws UnknownRequestException {
        Class<RequestType> requestClass = getRequestClass();
        if (requestClass.isInstance(requestRaw)) {
            RequestType request = requestClass.cast(requestRaw);
            return getResponse(collection, request);
        }
        throw new UnknownRequestException();
    }
}
