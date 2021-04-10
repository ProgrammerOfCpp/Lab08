package com.artyemlavrov.lab5.client.commands.interactive.server;

import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.Interpreter;
import com.artyemlavrov.lab5.request.GetInfoRequest;
import com.artyemlavrov.lab5.response.GetInfoResponse;

public class InfoCommand extends ServerCommand<GetInfoRequest, GetInfoResponse> {
    @Override
    public String getDescription() {
        return ": вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    protected GetInfoRequest buildRequest(Interpreter interpreter, IOManager ioManager) {
        return new GetInfoRequest();
    }

    @Override
    protected void onSuccess(Interpreter interpreter, IOManager ioManager, GetInfoResponse response) {

    }
}
