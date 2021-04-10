package com.artyemlavrov.lab5.client.commands.interactive.server;

import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.Interpreter;
import com.artyemlavrov.lab5.client.valuereaders.complex.WorkerReader;
import com.artyemlavrov.lab5.request.AddRequest;
import com.artyemlavrov.lab5.response.singleelement.AddResponse;
import com.artyemlavrov.lab5.types.Worker;

public class AddCommand extends ServerCommand<AddRequest, AddResponse> {
    @Override
    public String getDescription() {
        return "{element} : добавить новый элемент в коллекцию";
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    protected AddRequest buildRequest(Interpreter interpreter, IOManager ioManager) {
        Worker worker = new WorkerReader(ioManager).setNullable(false).read();
        return new AddRequest(worker);
    }

    @Override
    protected void onSuccess(Interpreter interpreter, IOManager ioManager, AddResponse response) {

    }
}
