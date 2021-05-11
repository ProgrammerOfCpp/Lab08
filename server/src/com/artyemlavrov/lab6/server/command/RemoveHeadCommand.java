package com.artyemlavrov.lab6.server.command;

import com.artyemlavrov.lab6.common.command.clientserver.ClientServerCommand;
import com.artyemlavrov.lab6.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab6.common.util.IOManager;
import com.artyemlavrov.lab6.server.request.RemoveHeadRequest;
import com.artyemlavrov.lab6.server.response.collectionemptiness.RemoveHeadResponse;

public class RemoveHeadCommand extends ClientServerCommand<RemoveHeadResponse> {

    public RemoveHeadCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

    @Override
    public String getDescription() {
        return ": вывести первый элемент коллекции и удалить его";
    }

    @Override
    public String getName() {
        return "remove_head";
    }

    @Override
    protected RemoveHeadRequest buildRequest(IOManager ioManager) {
        return new RemoveHeadRequest(getAuthentication());
    }

    @Override
    protected void onSuccess(IOManager ioManager, RemoveHeadResponse response) {
        if (response.isCollectionEmpty()) {
            ioManager.writeLine("Коллекция пустая.");
        } else {
            ioManager.writeLine("Элемент удалён.");
        }
    }

}
