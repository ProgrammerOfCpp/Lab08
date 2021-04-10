package com.artyemlavrov.lab5.client.commands.interactive.server;

import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.Interpreter;
import com.artyemlavrov.lab5.request.ClearRequest;
import com.artyemlavrov.lab5.response.collectionemptiness.ClearResponse;

public class ClearCommand extends ServerCommand<ClearRequest, ClearResponse> {
    @Override
    public String getDescription() {
        return ": очистить коллекцию";
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    protected ClearRequest buildRequest(Interpreter interpreter, IOManager ioManager) {
        return new ClearRequest();
    }

    @Override
    protected void onSuccess(Interpreter interpreter, IOManager ioManager, ClearResponse response) {

    }
}
