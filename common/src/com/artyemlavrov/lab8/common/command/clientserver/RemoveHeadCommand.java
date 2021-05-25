package com.artyemlavrov.lab8.common.command.clientserver;

import com.artyemlavrov.lab8.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab8.common.request.RemoveHeadRequest;
import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.response.collectionemptiness.RemoveHeadResponse;

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
