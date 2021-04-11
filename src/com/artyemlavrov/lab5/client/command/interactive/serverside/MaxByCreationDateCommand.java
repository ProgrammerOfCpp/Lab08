package com.artyemlavrov.lab5.client.command.interactive.serverside;

import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.common.request.GetMaxByCreationDateRequest;
import com.artyemlavrov.lab5.common.response.singleelement.GetMaxByCreationDateResponse;

public class MaxByCreationDateCommand extends ServersideCommand<GetMaxByCreationDateRequest, GetMaxByCreationDateResponse> {

    @Override
    public String getDescription() {
        return "вывести любой объект из коллекции, значение поля creationDate которого является максимальным";
    }

    @Override
    public String getName() {
        return "max_by_creation_date ";
    }

    @Override
    protected GetMaxByCreationDateRequest buildRequest(InterpreterLoop<Client> interpreterLoop, IOManager ioManager) {
        return new GetMaxByCreationDateRequest();
    }

    @Override
    protected void onSuccess(InterpreterLoop<Client> interpreterLoop, IOManager ioManager, GetMaxByCreationDateResponse response) {

    }
}
