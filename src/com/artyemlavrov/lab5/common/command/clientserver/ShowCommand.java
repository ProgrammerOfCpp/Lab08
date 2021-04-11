package com.artyemlavrov.lab5.common.command.clientserver;

import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.request.GetAllRequest;
import com.artyemlavrov.lab5.common.response.GetAllResponse;
import com.artyemlavrov.lab5.common.types.Worker;

import java.util.List;

public class ShowCommand extends ClientServerCommand<GetAllRequest, GetAllResponse> {

    @Override
    public String getDescription() {
        return ": вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    protected GetAllRequest buildRequest(IOManager ioManager) {
        return new GetAllRequest();
    }

    @Override
    protected void onSuccess(IOManager ioManager, GetAllResponse response) {
        List<Worker> elementsList = response.getElementsList();
        if (elementsList.isEmpty()) {
            ioManager.writeLine("Коллекция пустая.");
        } else {
            elementsList.forEach(element -> ioManager.write(element + " "));
            ioManager.newLine();
        }
    }

    @Override
    protected Class<GetAllResponse> getResponseClass() {
        return GetAllResponse.class;
    }
}
