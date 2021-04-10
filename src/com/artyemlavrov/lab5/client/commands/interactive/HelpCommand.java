package com.artyemlavrov.lab5.client.commands.interactive;

import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.CommandFactory;
import com.artyemlavrov.lab5.client.Interpreter;
import com.artyemlavrov.lab5.client.commands.Command;

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
    protected void execute(Interpreter interpreter, IOManager ioManager) {
        CommandFactory commandFactory = CommandFactory.getInstance();
        for (Command command : commandFactory.getAllCommands()) {
            ioManager.write(command.getName() + " ");
            ioManager.writeLine(command.getDescription());
        }
    }
}
