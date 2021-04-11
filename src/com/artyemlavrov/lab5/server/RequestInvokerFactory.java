package com.artyemlavrov.lab5.server;

import com.artyemlavrov.lab5.common.request.Request;
import com.artyemlavrov.lab5.common.response.Response;
import com.artyemlavrov.lab5.server.requestinvoker.*;

import java.util.Arrays;
import java.util.List;

public class RequestInvokerFactory {

    private final List<RequestInvoker<? extends  Request, ? extends Response>> requestInvokers = Arrays.asList(
            new AddInvoker(),
            new ClearInvoker(),
            new GetAllInvoker(),
            new GetInfoInvoker(),
            new GetMaxByCreationDateInvoker(),
            new GetStatusDescendingInvoker(),
            new GetSumOfSalaryInvoker(),
            new RemoveByIdInvoker(),
            new RemoveHeadInvoker(),
            new RemoveLowerInvoker(),
            new UpdateInvoker()
    );

    public RequestInvoker<? extends Request, ? extends Response> instantiate(Request request) throws UnknownRequestException {
        for (RequestInvoker<? extends  Request, ? extends Response> requestInvoker : requestInvokers) {
            Class<? extends Request> requestClass = requestInvoker.getRequestClass();
            if (requestClass.isInstance(request)) {
                return requestInvoker;
            }
        }
        throw new UnknownRequestException();
    }
}
