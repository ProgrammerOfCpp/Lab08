package com.artyemlavrov.lab5.client;

import com.artyemlavrov.lab5.client.command.ExitCommand;
import com.artyemlavrov.lab5.common.command.CommandFactory;

public class ClientCommandFactory extends CommandFactory {

    @Override
    protected void registerCommands() {
        super.registerCommands();
        registerCommand(new ExitCommand());
    }
}
