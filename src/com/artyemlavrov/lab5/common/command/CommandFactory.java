package com.artyemlavrov.lab5.common.command;

import com.artyemlavrov.lab5.common.application.Application;
import com.artyemlavrov.lab5.common.interpreter.CommandNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class CommandFactory<CommandType extends Command<? extends Application>> {
    private final Map<String, CommandType> commands = new HashMap<>();

    protected CommandFactory() {
        registerAllCommands();
    }

    protected abstract CommandType[] getCommandsForRegistration();

    private void registerAllCommands() {
        for (CommandType command : getCommandsForRegistration()) {
            registerCommand(command);
        }
    }

    private void registerCommand(CommandType command) {
        commands.put(command.getName(), command);
    }

    public CommandType instantiate(String commandName) throws CommandNotFoundException {
        String key = commandName.trim().toLowerCase();
        if (!commands.containsKey(key)) {
            throw new CommandNotFoundException();
        }
        return commands.get(key);
    }

    public Collection<CommandType> getAllCommands() {
        return commands.values();
    }
}
