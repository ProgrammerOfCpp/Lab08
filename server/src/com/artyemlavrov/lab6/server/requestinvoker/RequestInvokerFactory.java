package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.exception.RequestFailureException;
import com.artyemlavrov.lab6.common.exception.UnknownRequestException;
import com.artyemlavrov.lab6.common.request.Request;
import com.artyemlavrov.lab6.server.ServerApplication;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RequestInvokerFactory {

    private final ServerApplication application;

    private final List<Class<? extends RequestInvoker<? extends Request>>> requestInvokerClasses = Arrays.asList(
            AddInvoker.class,
            ClearInvoker.class,
            GetAllInvoker.class,
            GetInfoInvoker.class,
            GetMaxByCreationDateInvoker.class,
            GetStatusDescendingInvoker.class,
            GetSumOfSalaryInvoker.class,
            RemoveByIdInvoker.class,
            RemoveHeadInvoker.class,
            RemoveLowerInvoker.class,
            UpdateInvoker.class,
            RegisterInvoker.class,
            LoginInvoker.class
    );

    public RequestInvokerFactory(ServerApplication application) {
        this.application = application;
    }

    @SuppressWarnings("unchecked")
    public <REQUEST extends Request> RequestInvoker<REQUEST> instantiate(Request request) throws RequestFailureException {
        for (RequestInvoker<? extends Request> requestInvoker : getAllRequestInvokers()) {
            Class<? extends Request> requestClass = requestInvoker.getRequestClass();
            if (requestClass.isInstance(request)) {
                return (RequestInvoker<REQUEST>) requestInvoker;
            }
        }
        throw new UnknownRequestException();
    }

    private List<RequestInvoker<? extends Request>> getAllRequestInvokers() {
        return requestInvokerClasses.stream().map(requestInvokerClass -> {
            try {
                Constructor<? extends RequestInvoker<? extends Request>> requestInvokerConstructor = requestInvokerClass.getConstructor(ServerApplication.class);
                return requestInvokerConstructor.newInstance(application);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                System.err.println(e.getMessage());
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
