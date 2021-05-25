package com.artyemlavrov.lab8.common.command.clientserver;

import com.artyemlavrov.lab8.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.valuereader.complex.WorkerReader;
import com.artyemlavrov.lab8.common.valuereader.simple.number.IntegerReader;
import com.artyemlavrov.lab8.common.request.UpdateRequest;
import com.artyemlavrov.lab8.common.response.elementexistence.UpdateResponse;
import com.artyemlavrov.lab8.common.types.Worker;

public class UpdateCommand extends ClientServerCommand<UpdateResponse> {

    public UpdateCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

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
        return new UpdateRequest(getAuthentication(), id, value);
    }

    @Override
    protected void onSuccess(IOManager ioManager, UpdateResponse response) {
        if (response.hasElementExisted()) {
            ioManager.writeLine("Элемент обновлён.");
        } else {
            ioManager.writeLine("Элемент не найден.");
        }
    }

}
