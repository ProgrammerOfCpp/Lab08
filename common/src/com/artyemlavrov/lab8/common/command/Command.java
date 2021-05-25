package com.artyemlavrov.lab8.common.command;

import com.artyemlavrov.lab8.common.application.BasicApplication;
import com.artyemlavrov.lab8.common.interpreter.Interpreter;
import com.artyemlavrov.lab8.common.interpreter.InterpreterData;
import com.artyemlavrov.lab8.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab8.common.util.IOManager;

public abstract class Command {
    private final InterpreterLoop interpreterLoop;

    protected Command(InterpreterLoop interpreterLoop) {
        this.interpreterLoop = interpreterLoop;
    }

    public void execute() {
        IOManager ioManager = getIOManager();
        onExecute(ioManager);
    }

    protected abstract void onExecute(IOManager ioManager);

    public abstract String getDescription();

    public abstract String getName();

    protected InterpreterLoop getInterpreterLoop() {
        return this.interpreterLoop;
    }

    protected IOManager getIOManager() {
        InterpreterLoop interpreterLoop = getInterpreterLoop();
        return interpreterLoop.getIOManager();
    }

    protected BasicApplication getApplication() {
        InterpreterLoop interpreterLoop = getInterpreterLoop();
        return interpreterLoop.getApplication();
    }

    protected Interpreter getInterpreter() {
        InterpreterLoop interpreterLoop = getInterpreterLoop();
        return interpreterLoop.getInterpreter();
    }

    protected InterpreterData getInterpreterData() {
        Interpreter interpreter = getInterpreter();
        return interpreter.getData();
    }
}
