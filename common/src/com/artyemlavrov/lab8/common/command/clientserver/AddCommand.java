package com.artyemlavrov.lab8.common.command.clientserver;

import com.artyemlavrov.lab8.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.valuereader.complex.WorkerReader;
import com.artyemlavrov.lab8.common.request.AddRequest;
import com.artyemlavrov.lab8.common.response.singleelement.AddResponse;
import com.artyemlavrov.lab8.common.types.Worker;

public class AddCommand extends ClientServerCommand<AddResponse> {

    public AddCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

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
        return new AddRequest(getAuthentication(), worker);
    }

    @Override
    protected void onSuccess(IOManager ioManager, AddResponse response) {
        Worker element = response.getElement();
        ioManager.writeLine("Добавлен объект: " + element.toString());
    }

}
