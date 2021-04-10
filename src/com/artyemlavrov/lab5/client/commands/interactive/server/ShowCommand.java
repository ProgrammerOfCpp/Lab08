package com.artyemlavrov.lab5.client.commands.interactive.server;

import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.Interpreter;
import com.artyemlavrov.lab5.request.GetAllRequest;
import com.artyemlavrov.lab5.response.GetAllResponse;

public class ShowCommand extends ServerCommand<GetAllRequest, GetAllResponse> {
    @Override
    public String getDescription() {
        return ": вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    protected GetAllRequest buildRequest(Interpreter interpreter, IOManager ioManager) {
        return new GetAllRequest();
    }

    @Override
    protected void onSuccess(Interpreter interpreter, IOManager ioManager, GetAllResponse response) {

    }
}
