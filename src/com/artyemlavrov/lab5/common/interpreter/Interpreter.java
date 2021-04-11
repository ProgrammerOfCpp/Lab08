package com.artyemlavrov.lab5.common.interpreter;

import com.artyemlavrov.lab5.common.application.Application;
import com.artyemlavrov.lab5.common.command.Command;
import com.artyemlavrov.lab5.common.command.CommandFactory;

public class Interpreter<ApplicationType extends Application> {

    private final ApplicationType application;
    private final InterpreterData interpreterData = new InterpreterData();
    private final CommandFactory<? extends Command<ApplicationType>> commandFactory;

    public Interpreter(ApplicationType application, CommandFactory<? extends Command<ApplicationType>> commandFactory) {
        this.application = application;
        this.commandFactory = commandFactory;
    }

    public void run() {
        InterpreterLoop<ApplicationType> loop = new InterpreterLoop<>(this);
        loop.run();
    }

    public ApplicationType getApplication() {
        return application;
    }

    public InterpreterData getData() {
        return interpreterData;
    }

    public CommandFactory<? extends Command<ApplicationType>> getCommandFactory() {
        return commandFactory;
    }
}
