package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.exception.RequestFailureException;
import com.artyemlavrov.lab6.common.request.Request;
import com.artyemlavrov.lab6.common.response.Response;

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

    @SuppressWarnings("unchecked")
    public <RequestType extends Request, ResponseType extends Response> RequestInvoker<RequestType, ResponseType> instantiate(Request request) throws RequestFailureException {
        for (RequestInvoker<? extends  Request, ? extends Response> requestInvoker : requestInvokers) {
            Class<? extends Request> requestClass = requestInvoker.getRequestClass();
            if (requestClass.isInstance(request)) {
                return (RequestInvoker<RequestType, ResponseType>) requestInvoker;
            }
        }
        throw new RequestFailureException("Ошибка: не удаётся определить тип запроса.");
    }
}
