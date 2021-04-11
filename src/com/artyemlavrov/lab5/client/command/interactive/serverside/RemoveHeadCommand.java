package com.artyemlavrov.lab5.client.command.interactive.serverside;

import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.common.request.RemoveHeadRequest;
import com.artyemlavrov.lab5.common.response.collectionemptiness.RemoveHeadResponse;

public class RemoveHeadCommand extends ServersideCommand<RemoveHeadRequest, RemoveHeadResponse> {

    @Override
    public String getDescription() {
        return ": вывести первый элемент коллекции и удалить его";
    }

    @Override
    public String getName() {
        return "remove_head";
    }

    @Override
    protected RemoveHeadRequest buildRequest(InterpreterLoop<Client> interpreterLoop, IOManager ioManager) {
        return new RemoveHeadRequest();
    }

    @Override
    protected void onSuccess(InterpreterLoop<Client> interpreterLoop, IOManager ioManager, RemoveHeadResponse response) {

    }
}
