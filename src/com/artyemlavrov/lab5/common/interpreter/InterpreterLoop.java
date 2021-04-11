package com.artyemlavrov.lab5.common.interpreter;

import com.artyemlavrov.lab5.common.application.Application;
import com.artyemlavrov.lab5.common.command.Command;
import com.artyemlavrov.lab5.common.command.CommandFactory;
import com.artyemlavrov.lab5.common.io.IOManager;

import java.io.IOException;

enum InterpreterMode { CONSOLE, SCRIPT }

public class InterpreterLoop<ApplicationType extends Application> {

    private InterpreterMode mode = InterpreterMode.CONSOLE;
    private boolean stopFlag = false;
    private final IOManager ioManager = new IOManager();
    private final Interpreter<ApplicationType> interpreter;

    public InterpreterLoop(Interpreter<ApplicationType> interpreter) {
        this.interpreter = interpreter;
    }

    public void run() {
        while (shouldContinue()) {
            readAndExecuteCommand();
        }
    }

    public void runFromScript(String scriptPath) {
        try {
            ioManager.setInputFile(scriptPath);
            mode = InterpreterMode.SCRIPT;
            run();
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public void stop() {
        stopFlag = true;
    }

    public IOManager getIOManager() {
        return ioManager;
    }

    public InterpreterMode getMode() {
        return mode;
    }

    public ApplicationType getApplication() {
        return interpreter.getApplication();
    }

    public InterpreterData getInterpreterData() {
        return interpreter.getData();
    }

    public Interpreter<ApplicationType> getInterpreter() {
        return interpreter;
    }

    private boolean shouldContinue() {
        if (stopFlag) {
            return false;
        }
        switch (mode) {
            case CONSOLE:
                return true;
            case SCRIPT:
                return ioManager.hasNext();
        }
        return false;
    }

    private void readAndExecuteCommand() {
        try {
            Command<ApplicationType> command = readCommand();
            executeCommand(command);
        } catch (CommandNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private Command<ApplicationType> readCommand() throws CommandNotFoundException {
        String name;
        do {
            name = ioManager.readNext().trim();
        } while (name.isEmpty());
        CommandFactory<? extends Command<ApplicationType>> commandFactory = interpreter.getCommandFactory();
        return commandFactory.instantiate(name);
    }

    private void executeCommand(Command<ApplicationType> command) {
        command.execute(this);
        InterpreterData interpreterData = getInterpreterData();
        interpreterData.addToHistory(command.getName());
        ioManager.writeLine("");
    }
}
