package com.artyemlavrov.lab6.common.command;

import com.artyemlavrov.lab6.common.command.clientserver.*;
import com.artyemlavrov.lab6.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab6.common.exception.UnknownCommandException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class CommandFactory {

    private final List<Class<? extends Command>> commandClasses = new LinkedList<>();

    public CommandFactory() {
        addCommandClasses(commandClasses);
    }

    public Command instantiate(InterpreterLoop interpreterLoop, String commandName) throws UnknownCommandException {
        String key = commandName.trim().toLowerCase();
        for (Command command : getAllCommands(interpreterLoop)) {
            if (command.getName().equals(key)) {
                return command;
            }
        }
        throw new UnknownCommandException();
    }

    public List<Command> getAllCommands(InterpreterLoop interpreterLoop) {
        return commandClasses.stream().map((commandClass) -> {
            try {
                Constructor<? extends Command> commandConstructor = commandClass.getConstructor(InterpreterLoop.class);
                return commandConstructor.newInstance(interpreterLoop);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    protected void addCommandClasses(List<Class<? extends Command>> commandClasses) {
        commandClasses.add(InfoCommand.class);
        commandClasses.add(InfoCommand.class);
        commandClasses.add(ShowCommand.class);
        commandClasses.add(AddCommand.class);
        commandClasses.add(UpdateCommand.class);
        commandClasses.add(RemoveByIdCommand.class);
        commandClasses.add(SumOfSalaryCommand.class);
        commandClasses.add(MaxByCreationDateCommand.class);
        commandClasses.add(PrintFieldDescendingStatusCommand.class);
        commandClasses.add(ExitCommand.class);
        commandClasses.add(ExecuteScriptCommand.class);
        commandClasses.add(HelpCommand.class);
        commandClasses.add(HistoryCommand.class);
    }
}
