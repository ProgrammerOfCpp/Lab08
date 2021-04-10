package com.artyemlavrov.lab5.client;

import com.artyemlavrov.lab5.client.commands.*;
import com.artyemlavrov.lab5.client.commands.interactive.ExecuteScriptCommand;
import com.artyemlavrov.lab5.client.commands.interactive.HelpCommand;
import com.artyemlavrov.lab5.client.commands.interactive.HistoryCommand;
import com.artyemlavrov.lab5.client.commands.interactive.server.InfoCommand;
import com.artyemlavrov.lab5.client.commands.interactive.server.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Command> commands = new HashMap<>();
    private final static CommandFactory instance = new CommandFactory();

    CommandFactory() {
        registerAllCommands();
    }

    private void registerAllCommands() {
        registerCommand(new HelpCommand());
        registerCommand(new InfoCommand());
        registerCommand(new ShowCommand());
        registerCommand(new AddCommand());
        registerCommand(new UpdateCommand());
        registerCommand(new RemoveByIdCommand());
        registerCommand(new ClearCommand());
        registerCommand(new ExecuteScriptCommand());
        registerCommand(new ExitCommand());
        registerCommand(new RemoveHeadCommand());
        registerCommand(new RemoveLowerCommand());
        registerCommand(new HistoryCommand());
        registerCommand(new SumOfSalaryCommand());
        registerCommand(new MaxByCreationDateCommand());
        registerCommand(new PrintFieldDescendingStatusCommand());
    }

    private void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }

    public Command instantiate(String commandName) {
        return commands.get(commandName.trim().toLowerCase());
    }

    public Collection<Command> getAllCommands() {
        return commands.values();
    }

    public static CommandFactory getInstance() {
        return instance;
    }
}
