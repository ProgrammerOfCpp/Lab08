package com.artyemlavrov.lab8.common.interpreter;

import com.artyemlavrov.lab8.common.application.BasicApplication;
import com.artyemlavrov.lab8.common.command.Command;
import com.artyemlavrov.lab8.common.command.CommandFactory;
import com.artyemlavrov.lab8.common.exception.UnknownCommandException;
import com.artyemlavrov.lab8.common.util.IOManager;

import java.io.IOException;

enum InterpreterMode { CONSOLE, SCRIPT }

public class InterpreterLoop {

    private InterpreterMode mode = InterpreterMode.CONSOLE;
    private boolean stopFlag = false;
    private final IOManager ioManager = new IOManager();
    private final Interpreter interpreter;

    public InterpreterLoop(Interpreter interpreter) {
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

    public BasicApplication getApplication() {
        return interpreter.getApplication();
    }

    public InterpreterData getInterpreterData() {
        return interpreter.getData();
    }

    public Interpreter getInterpreter() {
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
            Command command = readCommand();
            executeCommand(command);
        } catch (UnknownCommandException e) {
            System.err.println(e.getMessage());
        }
    }

    private Command readCommand() throws UnknownCommandException {
        String name;
        do {
            name = ioManager.readNext().trim();
        } while (name.isEmpty());
        CommandFactory commandFactory = interpreter.getCommandFactory();
        return commandFactory.instantiate(this, name);
    }

    public void executeCommand(Command command) {
        IOManager ioManager = getIOManager();
        ioManager.setForceWrite(true);
        command.execute();
        ioManager.setForceWrite(false);

        InterpreterData interpreterData = getInterpreterData();
        interpreterData.addToHistory(command.getName());
    }
}
