package com.artyemlavrov.lab5.common.command.clientserver;

import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.common.application.Application;
import com.artyemlavrov.lab5.common.command.Command;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.request.Request;
import com.artyemlavrov.lab5.common.response.Response;
import com.artyemlavrov.lab5.server.Server;

public abstract class ClientServerCommand<RequestType extends Request, ResponseType extends Response> extends Command {

    @Override
    public void execute(InterpreterLoop interpreterLoop, IOManager ioManager) {
        Application application = interpreterLoop.getApplication();
        RequestType request = buildRequest(ioManager);
        try {
            ResponseType response = getResponse(application, request);
            onSuccess(ioManager, response);
        } catch (ClientServerCommandException e) {
            System.err.println(e.getMessage());
        }
    }

    private ResponseType getResponse(Application application, RequestType request) throws ClientServerCommandException {
        if (application instanceof Client) {
            Client client = (Client) application;
            return client.makeRequest(request, getResponseClass());
        }
        if (application instanceof Server) {
            Server server = (Server) application;
            return server.invokeRequest(request, getResponseClass());
        }
        throw new ClientServerCommandException();
    }

    protected abstract RequestType buildRequest(IOManager ioManager);

    protected abstract void onSuccess(IOManager ioManager, ResponseType response);

    protected abstract Class<ResponseType> getResponseClass();
}
