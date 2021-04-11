package com.artyemlavrov.lab5.client.command.interactive.serverside;

import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.common.request.GetAllRequest;
import com.artyemlavrov.lab5.common.response.GetAllResponse;

public class ShowCommand extends ServersideCommand<GetAllRequest, GetAllResponse> {

    @Override
    public String getDescription() {
        return ": вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    protected GetAllRequest buildRequest(InterpreterLoop<Client> interpreterLoop, IOManager ioManager) {
        return new GetAllRequest();
    }

    @Override
    protected void onSuccess(InterpreterLoop<Client> interpreterLoop, IOManager ioManager, GetAllResponse response) {

    }
}
