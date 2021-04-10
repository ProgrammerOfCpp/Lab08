package com.artyemlavrov.lab5.client.commands.interactive;

import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.Interpreter;
import com.artyemlavrov.lab5.client.commands.Command;

public abstract class InteractiveCommand extends Command {
    @Override
    public void execute(Interpreter interpreter) {
        IOManager ioManager = interpreter.getIOManager();
        execute(interpreter, ioManager);
    }

    protected abstract void execute(Interpreter interpreter, IOManager ioManager);
}
