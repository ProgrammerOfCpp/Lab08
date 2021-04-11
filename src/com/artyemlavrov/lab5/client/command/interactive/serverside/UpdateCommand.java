package com.artyemlavrov.lab5.client.command.interactive.serverside;

import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.common.valuereader.complex.WorkerReader;
import com.artyemlavrov.lab5.common.valuereader.simple.number.IntegerReader;
import com.artyemlavrov.lab5.common.request.UpdateRequest;
import com.artyemlavrov.lab5.common.response.elementexistence.UpdateResponse;
import com.artyemlavrov.lab5.common.types.Worker;

public class UpdateCommand extends ServersideCommand<UpdateRequest, UpdateResponse> {

    @Override
    public String getDescription() {
        return "id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    protected UpdateRequest buildRequest(InterpreterLoop<Client> interpreterLoop, IOManager ioManager) {
        Integer id = new IntegerReader(ioManager).setNullable(false).read();
        Worker value = new WorkerReader(ioManager).setNullable(false).read();
        return new UpdateRequest(id, value);
    }

    @Override
    protected void onSuccess(InterpreterLoop<Client> interpreterLoop, IOManager ioManager, UpdateResponse response) {

    }
}
