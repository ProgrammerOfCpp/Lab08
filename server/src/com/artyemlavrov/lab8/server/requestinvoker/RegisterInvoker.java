package com.artyemlavrov.lab8.server.requestinvoker;

import com.artyemlavrov.lab8.common.request.RegisterRequest;
import com.artyemlavrov.lab8.common.response.RegisterResponse;
import com.artyemlavrov.lab8.common.response.Response;
import com.artyemlavrov.lab8.common.types.Authentication;
import com.artyemlavrov.lab8.server.ServerApplication;
import com.artyemlavrov.lab8.server.database.AuthenticationProvider;

public class RegisterInvoker extends RequestInvoker<RegisterRequest> {
    public RegisterInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    protected Response buildResponse(RegisterRequest request) {
        Authentication authentication = request.getAuthentication();
        AuthenticationProvider authenticationProvider = getAuthenticationProvider();
        authenticationProvider.register(authentication);
        return new RegisterResponse(authentication);
    }

    @Override
    public Class<RegisterRequest> getRequestClass() {
        return RegisterRequest.class;
    }
}
