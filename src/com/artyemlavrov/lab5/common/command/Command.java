package com.artyemlavrov.lab5.common.command;

import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.common.io.IOManager;

public abstract class Command {

    public abstract void execute(InterpreterLoop interpreterLoop, IOManager ioManager);

    public abstract String getDescription();

    public abstract String getName();
}
