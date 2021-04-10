package com.artyemlavrov.lab5.client.commands.interactive.server;

import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.Interpreter;
import com.artyemlavrov.lab5.client.valuereaders.complex.WorkerReader;
import com.artyemlavrov.lab5.request.RemoveLowerRequest;
import com.artyemlavrov.lab5.response.RemoveLowerResponse;
import com.artyemlavrov.lab5.types.Worker;

public class RemoveLowerCommand extends ServerCommand<RemoveLowerRequest, RemoveLowerResponse> {
    @Override
    public String getDescription() {
        return "{element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }

    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    protected RemoveLowerRequest buildRequest(Interpreter interpreter, IOManager ioManager) {
        Worker element = new WorkerReader(ioManager).setNullable(false).read();
        return new RemoveLowerRequest(element);
    }

    @Override
    protected void onSuccess(Interpreter interpreter, IOManager ioManager, RemoveLowerResponse response) {

    }
}
