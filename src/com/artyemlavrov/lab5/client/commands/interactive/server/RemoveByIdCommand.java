package com.artyemlavrov.lab5.client.commands.interactive.server;

import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.Interpreter;
import com.artyemlavrov.lab5.client.valuereaders.simple.number.IntegerReader;
import com.artyemlavrov.lab5.request.RemoveByIdRequest;
import com.artyemlavrov.lab5.response.elementexistence.RemoveByIdResponse;

public class RemoveByIdCommand extends ServerCommand<RemoveByIdRequest, RemoveByIdResponse> {
    @Override
    public String getDescription() {
        return "id : удалить элемент из коллекции по его id";
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    protected RemoveByIdRequest buildRequest(Interpreter interpreter, IOManager ioManager) {
        Integer id = new IntegerReader(ioManager).setNullable(false).read();
        return new RemoveByIdRequest(id);
    }

    @Override
    protected void onSuccess(Interpreter interpreter, IOManager ioManager, RemoveByIdResponse response) {

    }
}
