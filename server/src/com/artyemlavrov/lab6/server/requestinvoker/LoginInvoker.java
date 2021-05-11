package com.artyemlavrov.lab6.server.requestinvoker;

import com.artyemlavrov.lab6.common.request.LoginRequest;
import com.artyemlavrov.lab6.common.response.LoginResponse;
import com.artyemlavrov.lab6.common.response.Response;
import com.artyemlavrov.lab6.common.types.Authentication;
import com.artyemlavrov.lab6.server.ServerApplication;
import com.artyemlavrov.lab6.server.database.AuthenticationProvider;

public class LoginInvoker extends RequestInvoker<LoginRequest>  {

    public LoginInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    protected Response buildResponse(LoginRequest request) {
        Authentication authentication = request.getAuthentication();
        AuthenticationProvider authenticationProvider = getAuthenticationProvider();
        authenticationProvider.login(authentication);
        return new LoginResponse(authentication);
    }

    @Override
    public Class<LoginRequest> getRequestClass() {
        return LoginRequest.class;
    }
}
