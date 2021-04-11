package com.artyemlavrov.lab5.common.command.clientserver;

import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.valuereader.simple.number.IntegerReader;
import com.artyemlavrov.lab5.common.request.RemoveByIdRequest;
import com.artyemlavrov.lab5.common.response.elementexistence.RemoveByIdResponse;

public class RemoveByIdCommand extends ClientServerCommand<RemoveByIdRequest, RemoveByIdResponse> {

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
        return new RemoveByIdRequest(id);
    }

    @Override
    protected void onSuccess(IOManager ioManager, RemoveByIdResponse response) {
        if (response.hasElementExisted()) {
            ioManager.writeLine("Элемент удалён.");
        } else {
            ioManager.writeLine("Элемент не найден.");
        }
    }

    @Override
    protected Class<RemoveByIdResponse> getResponseClass() {
        return RemoveByIdResponse.class;
    }
}
