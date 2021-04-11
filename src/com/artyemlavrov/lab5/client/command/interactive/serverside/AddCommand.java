package com.artyemlavrov.lab5.client.command.interactive.serverside;

import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.common.valuereader.complex.WorkerReader;
import com.artyemlavrov.lab5.common.request.AddRequest;
import com.artyemlavrov.lab5.common.response.singleelement.AddResponse;
import com.artyemlavrov.lab5.common.types.Worker;

public class AddCommand extends ServersideCommand<AddRequest, AddResponse> {

    @Override
    public String getDescription() {
        return "{element} : добавить новый элемент в коллекцию";
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    protected AddRequest buildRequest(InterpreterLoop<Client> interpreterLoop, IOManager ioManager) {
        Worker worker = new WorkerReader(ioManager).setNullable(false).read();
        return new AddRequest(worker);
    }

    @Override
    protected void onSuccess(InterpreterLoop<Client> interpreterLoop, IOManager ioManager, AddResponse response) {

    }
}
