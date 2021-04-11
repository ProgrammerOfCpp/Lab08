package com.artyemlavrov.lab5.client.command.interactive.serverside;

import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.common.request.GetInfoRequest;
import com.artyemlavrov.lab5.common.response.GetInfoResponse;

public class InfoCommand extends ServersideCommand<GetInfoRequest, GetInfoResponse> {

    @Override
    public String getDescription() {
        return ": вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    protected GetInfoRequest buildRequest(InterpreterLoop<Client> interpreterLoop, IOManager ioManager) {
        return new GetInfoRequest();
    }

    @Override
    protected void onSuccess(InterpreterLoop<Client> interpreterLoop, IOManager ioManager, GetInfoResponse response) {

    }
}
