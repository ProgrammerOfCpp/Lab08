package com.artyemlavrov.lab5.client.commands.interactive.server;

import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.Interpreter;
import com.artyemlavrov.lab5.request.RemoveHeadRequest;
import com.artyemlavrov.lab5.response.collectionemptiness.RemoveHeadResponse;

public class RemoveHeadCommand extends ServerCommand<RemoveHeadRequest, RemoveHeadResponse> {
    @Override
    public String getDescription() {
        return ": вывести первый элемент коллекции и удалить его";
    }

    @Override
    public String getName() {
        return "remove_head";
    }

    @Override
    protected RemoveHeadRequest buildRequest(Interpreter interpreter, IOManager ioManager) {
        return new RemoveHeadRequest();
    }

    @Override
    protected void onSuccess(Interpreter interpreter, IOManager ioManager, RemoveHeadResponse response) {

    }
}
