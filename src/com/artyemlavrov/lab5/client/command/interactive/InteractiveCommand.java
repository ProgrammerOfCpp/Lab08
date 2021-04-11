package com.artyemlavrov.lab5.client.command.interactive;

import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.client.command.ClientCommand;
import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;

public abstract class InteractiveCommand extends ClientCommand {

    @Override
    public void execute(InterpreterLoop<Client> interpreterLoop) {
        IOManager ioManager = interpreterLoop.getIOManager();
        execute(interpreterLoop, ioManager);
    }

    protected abstract void execute(InterpreterLoop<Client> interpreterLoop, IOManager ioManager);
}
