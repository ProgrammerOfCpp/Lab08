package com.artyemlavrov.lab6.common.command;

import com.artyemlavrov.lab6.common.request.RegisterRequest;
import com.artyemlavrov.lab6.common.response.RegisterResponse;
import com.artyemlavrov.lab6.common.application.Application;
import com.artyemlavrov.lab6.common.command.clientserver.ClientServerCommand;
import com.artyemlavrov.lab6.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab6.common.request.Request;
import com.artyemlavrov.lab6.common.types.Authentication;
import com.artyemlavrov.lab6.common.util.IOManager;
import com.artyemlavrov.lab6.common.valuereader.simple.StringReader;

public class RegisterCommand extends ClientServerCommand<RegisterResponse> {
    public RegisterCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

    @Override
    public String getDescription() {
        return "username password : регистрация нового пользователя";
    }

    @Override
    protected Request buildRequest(IOManager ioManager) {
        String username = new StringReader(ioManager).setCanBeEmpty(false).read();
        String password = new StringReader(ioManager).setCanBeEmpty(false).read();
        Authentication authentication = new Authentication(username, password);
        return new RegisterRequest(authentication);
    }

    @Override
    protected void onSuccess(IOManager ioManager, RegisterResponse response) {
        Application application = getApplication();
        application.setAuthentication(response.getAuthentication());
        ioManager.writeLine("Пользователь зарегестрирован.");
    }

    @Override
    public String getName() {
        return "register";
    }
}
