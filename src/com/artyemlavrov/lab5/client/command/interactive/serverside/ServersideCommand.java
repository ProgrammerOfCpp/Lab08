package com.artyemlavrov.lab5.client.command.interactive.serverside;

import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.client.command.interactive.InteractiveCommand;
import com.artyemlavrov.lab5.common.request.Request;
import com.artyemlavrov.lab5.common.response.Response;

public abstract class ServersideCommand<RequestType extends Request, ResponseType extends Response> extends InteractiveCommand {

    @Override
    public void execute(InterpreterLoop<Client> interpreterLoop, IOManager ioManager) {
        Client client = interpreterLoop.getApplication();
        RequestType request = buildRequest(interpreterLoop, ioManager);
        client.makeRequest(request, (ResponseType response) -> onSuccess(interpreterLoop, ioManager, response));
    }

    protected abstract RequestType buildRequest(InterpreterLoop<Client> interpreterLoop, IOManager ioManager);

    protected abstract void onSuccess(InterpreterLoop<Client> interpreterLoop, IOManager ioManager, ResponseType response);
}
