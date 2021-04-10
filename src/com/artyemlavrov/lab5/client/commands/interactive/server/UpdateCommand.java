package com.artyemlavrov.lab5.client.commands.interactive.server;

import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.Interpreter;
import com.artyemlavrov.lab5.client.valuereaders.complex.WorkerReader;
import com.artyemlavrov.lab5.client.valuereaders.simple.number.IntegerReader;
import com.artyemlavrov.lab5.request.UpdateRequest;
import com.artyemlavrov.lab5.response.elementexistence.UpdateResponse;
import com.artyemlavrov.lab5.types.Worker;

public class UpdateCommand extends ServerCommand<UpdateRequest, UpdateResponse> {
    @Override
    public String getDescription() {
        return "id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    protected UpdateRequest buildRequest(Interpreter interpreter, IOManager ioManager) {
        Integer id = new IntegerReader(ioManager).setNullable(false).read();
        Worker value = new WorkerReader(ioManager).setNullable(false).read();
        return new UpdateRequest(id, value);
    }

    @Override
    protected void onSuccess(Interpreter interpreter, IOManager ioManager, UpdateResponse response) {

    }
}
