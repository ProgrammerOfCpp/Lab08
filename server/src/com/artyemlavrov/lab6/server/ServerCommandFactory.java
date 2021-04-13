package com.artyemlavrov.lab6.server;

import com.artyemlavrov.lab6.common.command.CommandFactory;
import com.artyemlavrov.lab6.server.command.SaveCommand;

public class ServerCommandFactory extends CommandFactory {

    @Override
    protected void registerCommands() {
        super.registerCommands();
        registerCommand(new SaveCommand());
    }
}
