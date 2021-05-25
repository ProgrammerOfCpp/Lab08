package com.artyemlavrov.lab8.common.command.clientserver;

import com.artyemlavrov.lab8.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.request.GetInfoRequest;
import com.artyemlavrov.lab8.common.response.GetInfoResponse;

public class InfoCommand extends ClientServerCommand<GetInfoResponse> {

    public InfoCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

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
        return new GetInfoRequest(getAuthentication());
    }

    @Override
    protected void onSuccess(IOManager ioManager, GetInfoResponse response) {
        ioManager.writeLine("Тип коллекции: " + response.getCollectionType());
        ioManager.writeLine("Количество элементов: " + response.getElementsCount());
        ioManager.writeLine("Дата инициализации: " + response.getInitializationDate());
    }

}
