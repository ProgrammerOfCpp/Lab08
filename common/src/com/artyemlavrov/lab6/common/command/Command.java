package com.artyemlavrov.lab6.common.command;

import com.artyemlavrov.lab6.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab6.common.util.IOManager;

public abstract class Command {

    public abstract void execute(InterpreterLoop interpreterLoop, IOManager ioManager);

    public abstract String getDescription();

    public abstract String getName();
}
