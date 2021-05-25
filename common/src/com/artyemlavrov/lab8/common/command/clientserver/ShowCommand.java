package com.artyemlavrov.lab8.common.command.clientserver;

import com.artyemlavrov.lab8.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.request.GetAllRequest;
import com.artyemlavrov.lab8.common.response.GetAllResponse;
import com.artyemlavrov.lab8.common.types.Worker;

import java.util.List;

public class ShowCommand extends ClientServerCommand<GetAllResponse> {

    public ShowCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

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
        return new GetAllRequest(getAuthentication());
    }

    @Override
    protected void onSuccess(IOManager ioManager, GetAllResponse response) {
        List<Worker> elementsList = response.getElementsList();
        if (elementsList.isEmpty()) {
            ioManager.writeLine("Коллекция пустая.");
        } else {
            elementsList.forEach(ioManager::writeLine);
            ioManager.newLine();
        }
    }
}
