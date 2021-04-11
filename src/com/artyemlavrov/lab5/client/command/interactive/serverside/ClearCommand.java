package com.artyemlavrov.lab5.client.command.interactive.serverside;

import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.common.request.ClearRequest;
import com.artyemlavrov.lab5.common.response.collectionemptiness.ClearResponse;

public class ClearCommand extends ServersideCommand<ClearRequest, ClearResponse> {

    @Override
    public String getDescription() {
        return ": очистить коллекцию";
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    protected ClearRequest buildRequest(InterpreterLoop<Client> interpreterLoop, IOManager ioManager) {
        return new ClearRequest();
    }

    @Override
    protected void onSuccess(InterpreterLoop<Client> interpreterLoop, IOManager ioManager, ClearResponse response) {

    }
}
