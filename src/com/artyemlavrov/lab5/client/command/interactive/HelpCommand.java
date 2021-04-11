package com.artyemlavrov.lab5.client.command.interactive;

import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.common.command.Command;
import com.artyemlavrov.lab5.common.command.CommandFactory;
import com.artyemlavrov.lab5.common.interpreter.Interpreter;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.common.io.IOManager;

public class HelpCommand extends InteractiveCommand {

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    protected void execute(InterpreterLoop<Client> interpreterLoop, IOManager ioManager) {
        Interpreter<Client> interpreter = interpreterLoop.getInterpreter();
        CommandFactory<? extends Command<Client>> commandFactory = interpreter.getCommandFactory();
        for (Command<Client> command : commandFactory.getAllCommands()) {
            ioManager.write(command.getName() + " ");
            ioManager.writeLine(command.getDescription());
        }
    }
}
