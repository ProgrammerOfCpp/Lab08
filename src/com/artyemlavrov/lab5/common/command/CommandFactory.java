package com.artyemlavrov.lab5.common.command;

import com.artyemlavrov.lab5.common.command.clientserver.*;
import com.artyemlavrov.lab5.common.interpreter.UnknownCommandException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class CommandFactory {
    private final Map<String, Command> commands = new HashMap<>();

    protected CommandFactory() {
        registerCommands();
    }

    protected void registerCommands() {
        registerCommand(new InfoCommand());
        registerCommand(new ShowCommand());
        registerCommand(new AddCommand());
        registerCommand(new UpdateCommand());
        registerCommand(new RemoveByIdCommand());
        registerCommand(new ClearCommand());
        registerCommand(new RemoveHeadCommand());
        registerCommand(new RemoveLowerCommand());
        registerCommand(new SumOfSalaryCommand());
        registerCommand(new MaxByCreationDateCommand());
        registerCommand(new PrintFieldDescendingStatusCommand());

        registerCommand(new ExecuteScriptCommand());
        registerCommand(new HelpCommand());
        registerCommand(new HistoryCommand());
    }

    public void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }

    public Command instantiate(String commandName) throws UnknownCommandException {
        String key = commandName.trim().toLowerCase();
        if (!commands.containsKey(key)) {
            throw new UnknownCommandException();
        }
        return commands.get(key);
    }

    public Collection<Command> getAllCommands() {
        return commands.values();
    }
}
