package com.artyemlavrov.lab5.client.command.interactive.serverside;

import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.common.valuereader.simple.number.IntegerReader;
import com.artyemlavrov.lab5.common.request.RemoveByIdRequest;
import com.artyemlavrov.lab5.common.response.elementexistence.RemoveByIdResponse;

public class RemoveByIdCommand extends ServersideCommand<RemoveByIdRequest, RemoveByIdResponse> {

    @Override
    public String getDescription() {
        return "id : удалить элемент из коллекции по его id";
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    protected RemoveByIdRequest buildRequest(InterpreterLoop<Client> interpreterLoop, IOManager ioManager) {
        Integer id = new IntegerReader(ioManager).setNullable(false).read();
        return new RemoveByIdRequest(id);
    }

    @Override
    protected void onSuccess(InterpreterLoop<Client> interpreterLoop, IOManager ioManager, RemoveByIdResponse response) {

    }
}
