package com.artyemlavrov.lab5.client.commands.interactive.server;

import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.Interpreter;
import com.artyemlavrov.lab5.request.GetMaxByCreationDateRequest;
import com.artyemlavrov.lab5.response.singleelement.GetMaxByCreationDateResponse;

public class MaxByCreationDateCommand extends ServerCommand<GetMaxByCreationDateRequest, GetMaxByCreationDateResponse> {
    @Override
    public String getDescription() {
        return "вывести любой объект из коллекции, значение поля creationDate которого является максимальным";
    }

    @Override
    public String getName() {
        return "max_by_creation_date ";
    }

    @Override
    protected GetMaxByCreationDateRequest buildRequest(Interpreter interpreter, IOManager ioManager) {
        return new GetMaxByCreationDateRequest();
    }

    @Override
    protected void onSuccess(Interpreter interpreter, IOManager ioManager, GetMaxByCreationDateResponse response) {

    }
}
