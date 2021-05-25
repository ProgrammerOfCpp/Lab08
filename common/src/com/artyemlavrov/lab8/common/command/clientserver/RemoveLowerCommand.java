package com.artyemlavrov.lab8.common.command.clientserver;

import com.artyemlavrov.lab8.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab8.common.request.RemoveLowerRequest;
import com.artyemlavrov.lab8.common.response.RemoveLowerResponse;
import com.artyemlavrov.lab8.common.types.Worker;
import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.valuereader.complex.WorkerReader;

public class RemoveLowerCommand extends ClientServerCommand<RemoveLowerResponse> {

    public RemoveLowerCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

    @Override
    public String getDescription() {
        return "{element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }

    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    protected void onSuccess(IOManager ioManager, RemoveLowerResponse response) {
        if (response.haveLowerElementsExisted()) {
            ioManager.writeLine("Коллекция пустая.");
        } else {
            ioManager.writeLine("Элемент удалён.");
        }
    }

    @Override
    protected RemoveLowerRequest buildRequest(IOManager ioManager) {
        Worker element = new WorkerReader(ioManager).setNullable(false).read();
        return new RemoveLowerRequest(getAuthentication(), element);
    }
}
