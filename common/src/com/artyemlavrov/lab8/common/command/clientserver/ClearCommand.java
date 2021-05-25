package com.artyemlavrov.lab8.common.command.clientserver;

import com.artyemlavrov.lab8.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.request.ClearRequest;
import com.artyemlavrov.lab8.common.response.collectionemptiness.ClearResponse;

public class ClearCommand extends ClientServerCommand<ClearResponse> {

    public ClearCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

    @Override
    public String getDescription() {
        return ": очистить коллекцию";
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    protected ClearRequest buildRequest(IOManager ioManager) {
        return new ClearRequest(getAuthentication());
    }

    @Override
    protected void onSuccess(IOManager ioManager, ClearResponse response) {
        if (response.isCollectionEmpty()) {
            ioManager.writeLine("Коллекция пустая.");
        } else {
            ioManager.writeLine("Коллекция очищена.");
        }
    }

}
