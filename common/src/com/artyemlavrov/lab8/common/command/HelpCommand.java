package com.artyemlavrov.lab8.common.command;

import com.artyemlavrov.lab8.common.interpreter.Interpreter;
import com.artyemlavrov.lab8.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab8.common.util.IOManager;

public class HelpCommand extends Command {

    public HelpCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

    @Override
    public void onExecute(IOManager ioManager) {
        InterpreterLoop interpreterLoop = getInterpreterLoop();
        Interpreter interpreter = interpreterLoop.getInterpreter();
        CommandFactory commandFactory = interpreter.getCommandFactory();
        for (Command command : commandFactory.getAllCommands(interpreterLoop)) {
            ioManager.write(command.getName() + " ");
            ioManager.writeLine(command.getDescription());
        }
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }

    @Override
    public String getName() {
        return "help";
    }
}
