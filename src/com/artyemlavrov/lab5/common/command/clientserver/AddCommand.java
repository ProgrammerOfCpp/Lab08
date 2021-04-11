package com.artyemlavrov.lab5.common.command.clientserver;

import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.valuereader.complex.WorkerReader;
import com.artyemlavrov.lab5.common.request.AddRequest;
import com.artyemlavrov.lab5.common.response.singleelement.AddResponse;
import com.artyemlavrov.lab5.common.types.Worker;

public class AddCommand extends ClientServerCommand<AddRequest, AddResponse> {

    @Override
    public String getDescription() {
        return "{element} : добавить новый элемент в коллекцию";
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    protected AddRequest buildRequest(IOManager ioManager) {
        Worker worker = new WorkerReader(ioManager).setNullable(false).read();
        return new AddRequest(worker);
    }

    @Override
    protected void onSuccess(IOManager ioManager, AddResponse response) {
        Worker element = response.getElement();
        ioManager.writeLine("Добавлен объект: " + element.toString());
    }

    @Override
    protected Class<AddResponse> getResponseClass() {
        return AddResponse.class;
    }
}
