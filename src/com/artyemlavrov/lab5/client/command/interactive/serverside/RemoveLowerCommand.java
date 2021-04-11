package com.artyemlavrov.lab5.client.command.interactive.serverside;

import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.common.valuereader.complex.WorkerReader;
import com.artyemlavrov.lab5.common.request.RemoveLowerRequest;
import com.artyemlavrov.lab5.common.response.RemoveLowerResponse;
import com.artyemlavrov.lab5.common.types.Worker;

public class RemoveLowerCommand extends ServersideCommand<RemoveLowerRequest, RemoveLowerResponse> {

    @Override
    public String getDescription() {
        return "{element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }

    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    protected void onSuccess(InterpreterLoop<Client> interpreterLoop, IOManager ioManager, RemoveLowerResponse response) {

    }

    @Override
    protected RemoveLowerRequest buildRequest(InterpreterLoop<Client> interpreterLoop, IOManager ioManager) {
        Worker element = new WorkerReader(ioManager).setNullable(false).read();
        return new RemoveLowerRequest(element);
    }
}
