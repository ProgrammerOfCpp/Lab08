package com.artyemlavrov.lab6.server.command;

import com.artyemlavrov.lab6.common.command.clientserver.ClientServerCommand;
import com.artyemlavrov.lab6.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab6.common.util.IOManager;
import com.artyemlavrov.lab6.common.valuereader.complex.WorkerReader;
import com.artyemlavrov.lab6.server.request.RemoveLowerRequest;
import com.artyemlavrov.lab6.server.RemoveLowerResponse;
import com.artyemlavrov.lab6.common.types.Worker;

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
