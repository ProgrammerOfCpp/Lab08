package com.artyemlavrov.lab8.server.requestinvoker;

import com.artyemlavrov.lab8.common.request.Request;
import com.artyemlavrov.lab8.common.response.Response;
import com.artyemlavrov.lab8.common.response.ResponseError;
import com.artyemlavrov.lab8.server.ServerApplication;
import com.artyemlavrov.lab8.server.database.AuthenticationProvider;
import com.artyemlavrov.lab8.server.database.CollectionProvider;
import com.artyemlavrov.lab8.server.database.Database;

public abstract class RequestInvoker<REQUEST extends Request> {

    private final ServerApplication application;

    public RequestInvoker(ServerApplication application) {
        this.application = application;
    }

    public Response invoke(REQUEST request) {
        try {
            return buildResponse(request);
        } catch (Throwable e) {
            return new ResponseError(request.getAuthentication(), e.getMessage());
        }
    }

    protected abstract Response buildResponse(REQUEST request);

    public abstract Class<REQUEST> getRequestClass();

    protected AuthenticationProvider getAuthenticationProvider() {
        return application.getAuthenticationProvider();
    }

    protected CollectionProvider getCollectionProvider(Request request) {
        Database database = application.getDatabase();
        return new CollectionProvider(database, request.getAuthentication());
    }
}
