package com.artyemlavrov.lab5.common.command.clientserver;

import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.request.ClearRequest;
import com.artyemlavrov.lab5.common.response.collectionemptiness.ClearResponse;

public class ClearCommand extends ClientServerCommand<ClearRequest, ClearResponse> {

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
        return new ClearRequest();
    }

    @Override
    protected void onSuccess(IOManager ioManager, ClearResponse response) {
        if (response.isCollectionEmpty()) {
            ioManager.writeLine("Коллекция пустая.");
        } else {
            ioManager.writeLine("Коллекция очищена.");
        }
    }

    @Override
    protected Class<ClearResponse> getResponseClass() {
        return ClearResponse.class;
    }
}
