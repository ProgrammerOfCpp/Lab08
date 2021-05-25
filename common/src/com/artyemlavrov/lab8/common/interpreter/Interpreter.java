package com.artyemlavrov.lab8.common.interpreter;

import com.artyemlavrov.lab8.common.application.BasicApplication;
import com.artyemlavrov.lab8.common.command.CommandFactory;

public class Interpreter {

    private final BasicApplication application;
    private final InterpreterData interpreterData = new InterpreterData();
    private final CommandFactory commandFactory;
    private final InterpreterLoop loop = new InterpreterLoop(this);

    public Interpreter(BasicApplication application, CommandFactory commandFactory) {
        this.application = application;
        this.commandFactory = commandFactory;
    }

    public void start() {
        Thread thread = new Thread(loop::run);
        thread.setDaemon(true);
        thread.start();
    }

    public void stop() {
        loop.stop();
    }

    public BasicApplication getApplication() {
        return application;
    }

    public InterpreterData getData() {
        return interpreterData;
    }

    public CommandFactory getCommandFactory() {
        return commandFactory;
    }
}
