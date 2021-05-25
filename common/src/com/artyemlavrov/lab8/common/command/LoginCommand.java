package com.artyemlavrov.lab8.common.command;

import com.artyemlavrov.lab8.common.request.LoginRequest;
import com.artyemlavrov.lab8.common.response.LoginResponse;
import com.artyemlavrov.lab8.common.application.BasicApplication;
import com.artyemlavrov.lab8.common.command.clientserver.ClientServerCommand;
import com.artyemlavrov.lab8.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab8.common.request.Request;
import com.artyemlavrov.lab8.common.types.Authentication;
import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.valuereader.simple.StringReader;

public class LoginCommand extends ClientServerCommand<LoginResponse> {

    public LoginCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

    @Override
    protected Request buildRequest(IOManager ioManager) {
        String username = new StringReader(ioManager).setCanBeEmpty(false).read();
        String password = new StringReader(ioManager).setCanBeEmpty(false).read();
        Authentication authentication = new Authentication(username, password);
        return new LoginRequest(authentication);
    }

    @Override
    protected void onSuccess(IOManager ioManager, LoginResponse response) {
        ioManager.writeLine("Вход осуществлён успешно.");
        BasicApplication application = getApplication();
        application.setAuthentication(response.getAuthentication());
    }

    @Override
    public String getDescription() {
        return "username password : аутентификация по имени и паролю";
    }

    @Override
    public String getName() {
        return "login";
    }
}
