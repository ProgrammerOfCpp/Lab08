package com.artyemlavrov.lab8.server.requestinvoker;

import com.artyemlavrov.lab8.common.request.LoginRequest;
import com.artyemlavrov.lab8.common.response.LoginResponse;
import com.artyemlavrov.lab8.common.response.Response;
import com.artyemlavrov.lab8.common.types.Worker;
import com.artyemlavrov.lab8.server.ServerApplication;
import com.artyemlavrov.lab8.server.database.CollectionProvider;

import java.util.List;

public class LoginInvoker extends RequestInvoker<LoginRequest>  {

    public LoginInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    protected Response buildResponse(LoginRequest request) {
        CollectionProvider collectionProvider = getCollectionProvider(request);
        List<Worker> elementsList = collectionProvider.getAll();
        return new LoginResponse(request.getAuthentication(), elementsList);
    }

    @Override
    public Class<LoginRequest> getRequestClass() {
        return LoginRequest.class;
    }
}
