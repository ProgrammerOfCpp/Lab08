package com.artyemlavrov.lab8.client;

import com.artyemlavrov.lab8.common.command.Command;
import com.artyemlavrov.lab8.common.command.CommandFactory;

import java.util.List;

public class ClientCommandFactory extends CommandFactory {
    @Override
    protected void addCommandClasses(List<Class<? extends Command>> commandClasses) {
        super.addCommandClasses(commandClasses);
    }
}
