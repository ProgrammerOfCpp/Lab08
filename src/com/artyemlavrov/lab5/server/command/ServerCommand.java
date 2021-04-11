package com.artyemlavrov.lab5.server.command;

import com.artyemlavrov.lab5.common.application.Application;
import com.artyemlavrov.lab5.common.command.Command;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.server.Server;
import com.artyemlavrov.lab5.server.WorkersCollection;

public abstract class ServerCommand extends Command {

    @Override
    public void execute(InterpreterLoop interpreterLoop, IOManager ioManager) {
        Application application = interpreterLoop.getApplication();
        if (application instanceof Server) {
            Server server = (Server) application;
            WorkersCollection collection = server.getCollection();
            execute(ioManager, collection);
        }
    }

    protected abstract void execute(IOManager ioManager, WorkersCollection collection);
}
