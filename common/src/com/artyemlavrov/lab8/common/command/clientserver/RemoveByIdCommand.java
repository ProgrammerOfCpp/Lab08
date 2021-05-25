package com.artyemlavrov.lab8.common.command.clientserver;

import com.artyemlavrov.lab8.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.valuereader.simple.number.IntegerReader;
import com.artyemlavrov.lab8.common.request.RemoveByIdRequest;
import com.artyemlavrov.lab8.common.response.elementexistence.RemoveByIdResponse;

public class RemoveByIdCommand extends ClientServerCommand<RemoveByIdResponse> {

    public RemoveByIdCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

    @Override
    public String getDescription() {
        return "id : удалить элемент из коллекции по его id";
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    protected RemoveByIdRequest buildRequest(IOManager ioManager) {
        Integer id = new IntegerReader(ioManager).setNullable(false).read();
        return new RemoveByIdRequest(getAuthentication(), id);
    }

    @Override
    protected void onSuccess(IOManager ioManager, RemoveByIdResponse response) {
        if (response.hasElementExisted()) {
            ioManager.writeLine("Элемент удалён.");
        } else {
            ioManager.writeLine("Элемент не найден.");
        }
    }

}
