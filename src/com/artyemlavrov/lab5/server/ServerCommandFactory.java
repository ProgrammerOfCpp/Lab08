package com.artyemlavrov.lab5.server;

import com.artyemlavrov.lab5.common.command.CommandFactory;
import com.artyemlavrov.lab5.server.command.SaveCommand;

public class ServerCommandFactory extends CommandFactory {

    @Override
    protected void registerCommands() {
        super.registerCommands();
        registerCommand(new SaveCommand());
    }
}
