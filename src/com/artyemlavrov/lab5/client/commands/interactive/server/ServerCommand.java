package com.artyemlavrov.lab5.client.commands.interactive.server;

import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.client.Interpreter;
import com.artyemlavrov.lab5.client.commands.interactive.InteractiveCommand;
import com.artyemlavrov.lab5.request.Request;
import com.artyemlavrov.lab5.response.Response;

public abstract class ServerCommand<RequestType extends Request, ResponseType extends Response> extends InteractiveCommand {
    @Override
    public void execute(Interpreter interpreter, IOManager ioManager) {
        Client client = interpreter.getClient();
        RequestType request = buildRequest(interpreter, ioManager);
        client.makeRequest(request, (ResponseType response) -> onSuccess(interpreter, ioManager, response));
    }

    protected abstract RequestType buildRequest(Interpreter interpreter, IOManager ioManager);

    protected abstract void onSuccess(Interpreter interpreter, IOManager ioManager, ResponseType response);
}
