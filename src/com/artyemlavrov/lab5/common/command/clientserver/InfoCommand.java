package com.artyemlavrov.lab5.common.command.clientserver;

import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.request.GetInfoRequest;
import com.artyemlavrov.lab5.common.response.GetInfoResponse;

public class InfoCommand extends ClientServerCommand<GetInfoRequest, GetInfoResponse> {

    @Override
    public String getDescription() {
        return ": вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    protected GetInfoRequest buildRequest(IOManager ioManager) {
        return new GetInfoRequest();
    }

    @Override
    protected void onSuccess(IOManager ioManager, GetInfoResponse response) {
        ioManager.writeLine("Тип коллекции: " + response.getCollectionType());
        ioManager.writeLine("Количество элементов: " + response.getElementsCount());
        ioManager.writeLine("Дата инициализации: " + response.getInitializationDate());
    }

    @Override
    protected Class<GetInfoResponse> getResponseClass() {
        return GetInfoResponse.class;
    }
}
