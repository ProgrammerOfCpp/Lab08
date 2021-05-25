package com.artyemlavrov.lab8.common.command.clientserver;

import com.artyemlavrov.lab8.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab8.common.response.singleelement.GetMaxByCreationDateResponse;
import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.request.GetMaxByCreationDateRequest;
import com.artyemlavrov.lab8.common.types.Worker;

public class MaxByCreationDateCommand extends ClientServerCommand<GetMaxByCreationDateResponse> {

    public MaxByCreationDateCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

    @Override
    public String getDescription() {
        return "вывести любой объект из коллекции, значение поля creationDate которого является максимальным";
    }

    @Override
    public String getName() {
        return "max_by_creation_date ";
    }

    @Override
    protected GetMaxByCreationDateRequest buildRequest(IOManager ioManager) {
        return new GetMaxByCreationDateRequest(getAuthentication());
    }

    @Override
    protected void onSuccess(IOManager ioManager, GetMaxByCreationDateResponse response) {
        Worker element = response.getElement();
        if (element == null) {
            ioManager.writeLine("Коллекция пустая.");
        } else {
            ioManager.writeLine(element);
        }
    }
}
