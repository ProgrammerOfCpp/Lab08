package com.artyemlavrov.lab5.client.commands.interactive.server;

import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.Interpreter;
import com.artyemlavrov.lab5.request.GetStatusDescendingRequest;
import com.artyemlavrov.lab5.response.GetStatusDescendingResponse;

public class PrintFieldDescendingStatusCommand extends ServerCommand<GetStatusDescendingRequest, GetStatusDescendingResponse> {
    @Override
    public String getDescription() {
        return ": вывести значения поля status всех элементов в порядке убывания";
    }

    @Override
    public String getName() {
        return "print_field_descending_status ";
    }

    @Override
    protected GetStatusDescendingRequest buildRequest(Interpreter interpreter, IOManager ioManager) {
        return new GetStatusDescendingRequest();
    }

    @Override
    protected void onSuccess(Interpreter interpreter, IOManager ioManager, GetStatusDescendingResponse response) {

    }
}
