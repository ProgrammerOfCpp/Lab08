package com.artyemlavrov.lab5.client.command.interactive.serverside;

import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.common.request.GetStatusDescendingRequest;
import com.artyemlavrov.lab5.common.response.GetStatusDescendingResponse;

public class PrintFieldDescendingStatusCommand extends ServersideCommand<GetStatusDescendingRequest, GetStatusDescendingResponse> {

    @Override
    public String getDescription() {
        return ": вывести значения поля status всех элементов в порядке убывания";
    }

    @Override
    public String getName() {
        return "print_field_descending_status ";
    }

    @Override
    protected GetStatusDescendingRequest buildRequest(InterpreterLoop<Client> interpreterLoop, IOManager ioManager) {
        return new GetStatusDescendingRequest();
    }

    @Override
    protected void onSuccess(InterpreterLoop<Client> interpreterLoop, IOManager ioManager, GetStatusDescendingResponse response) {

    }
}
