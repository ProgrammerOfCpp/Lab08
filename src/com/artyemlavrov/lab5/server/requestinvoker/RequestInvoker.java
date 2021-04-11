package com.artyemlavrov.lab5.server.requestinvoker;

import com.artyemlavrov.lab5.common.request.Request;
import com.artyemlavrov.lab5.common.response.Response;
import com.artyemlavrov.lab5.server.WorkersCollection;

public interface RequestInvoker<RequestType extends Request, ResponseType extends Response> {

    ResponseType invoke(WorkersCollection collection, RequestType request);
}
