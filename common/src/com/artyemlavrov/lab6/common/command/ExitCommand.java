package com.artyemlavrov.lab6.common.command;

import com.artyemlavrov.lab6.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab6.common.util.IOManager;

public class ExitCommand extends Command {

    public ExitCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

    @Override
    public void onExecute(IOManager ioManager) {
        InterpreterLoop interpreterLoop = getInterpreterLoop();
        interpreterLoop.stop();
    }

    @Override
    public String getDescription() {
        return ": завершить программу (без сохранения в файл)";
    }

    @Override
    public String getName() {
        return "exit";
    }
}
