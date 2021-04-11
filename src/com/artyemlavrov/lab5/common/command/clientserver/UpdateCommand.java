package com.artyemlavrov.lab5.common.command.clientserver;

import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.valuereader.complex.WorkerReader;
import com.artyemlavrov.lab5.common.valuereader.simple.number.IntegerReader;
import com.artyemlavrov.lab5.common.request.UpdateRequest;
import com.artyemlavrov.lab5.common.response.elementexistence.UpdateResponse;
import com.artyemlavrov.lab5.common.types.Worker;

public class UpdateCommand extends ClientServerCommand<UpdateRequest, UpdateResponse> {

    @Override
    public String getDescription() {
        return "id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    protected UpdateRequest buildRequest(IOManager ioManager) {
        Integer id = new IntegerReader(ioManager).setNullable(false).read();
        Worker value = new WorkerReader(ioManager).setNullable(false).read();
        return new UpdateRequest(id, value);
    }

    @Override
    protected void onSuccess(IOManager ioManager, UpdateResponse response) {
        if (response.hasElementExisted()) {
            ioManager.writeLine("Элемент обновлён.");
        } else {
            ioManager.writeLine("Элемент не найден.");
        }
    }

    @Override
    protected Class<UpdateResponse> getResponseClass() {
        return UpdateResponse.class;
    }
}
