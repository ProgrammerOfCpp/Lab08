package com.artyemlavrov.lab6.common.command.clientserver;

import com.artyemlavrov.lab6.common.application.Application;
import com.artyemlavrov.lab6.common.command.Command;
import com.artyemlavrov.lab6.common.exception.RequestFailureException;
import com.artyemlavrov.lab6.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab6.common.request.Request;
import com.artyemlavrov.lab6.common.response.Response;
import com.artyemlavrov.lab6.common.util.IOManager;

public abstract class ClientServerCommand<RequestType extends Request, ResponseType extends Response> extends Command {

    @Override
    public void execute(InterpreterLoop interpreterLoop, IOManager ioManager) {
        try {
            Application application = interpreterLoop.getApplication();
            RequestType request = buildRequest(ioManager);
            ResponseType response = application.getResponse(request);
            onSuccess(ioManager, response);
        } catch (RequestFailureException e) {
            System.err.println(e.getMessage());
        }
    }

    protected abstract RequestType buildRequest(IOManager ioManager);

    protected abstract void onSuccess(IOManager ioManager, ResponseType response);

}
