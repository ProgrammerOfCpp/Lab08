package com.artyemlavrov.lab8.common.command.clientserver;

import com.artyemlavrov.lab8.common.application.BasicApplication;
import com.artyemlavrov.lab8.common.command.Command;
import com.artyemlavrov.lab8.common.exception.RequestFailureException;
import com.artyemlavrov.lab8.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab8.common.request.Request;
import com.artyemlavrov.lab8.common.response.Response;
import com.artyemlavrov.lab8.common.response.ResponseError;
import com.artyemlavrov.lab8.common.types.Authentication;
import com.artyemlavrov.lab8.common.util.IOManager;

public abstract class ClientServerCommand<RESPONSE extends Response> extends Command {

    public ClientServerCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

    @Override
    public void onExecute(IOManager ioManager) {
        try {
            Request request = buildRequest(ioManager);
            BasicApplication application = getApplication();
            Response response = application.getResponse(request);
            onResponse(ioManager, response);
        } catch (RequestFailureException e) {
            System.err.println(e.getMessage());
        }
    }

    protected abstract Request buildRequest(IOManager ioManager);

    @SuppressWarnings("unchecked")
    private void onResponse(IOManager ioManager, Response response) {
        if (response instanceof ResponseError) {
            onError(ioManager, (ResponseError) response);
        } else {
            try {
                onSuccess(ioManager, (RESPONSE) response);
            } catch (Exception e) {
                ioManager.writeLine("Ошибка: не удаётся обработать ответ сервера.");
            }
        }
    }

    private void onError(IOManager ioManager, ResponseError error) {
        ioManager.writeLine(error.getMessage());
    }

    protected abstract void onSuccess(IOManager ioManager, RESPONSE response);

    protected Authentication getAuthentication() {
        BasicApplication application = getApplication();
        return application.getAuthentication();
    }
}
