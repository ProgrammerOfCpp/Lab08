package com.artyemlavrov.lab8.server;

import com.artyemlavrov.lab8.common.command.Command;
import com.artyemlavrov.lab8.common.command.CommandFactory;
import com.artyemlavrov.lab8.common.command.clientserver.ClearCommand;
import com.artyemlavrov.lab8.common.command.clientserver.RemoveHeadCommand;
import com.artyemlavrov.lab8.common.command.clientserver.RemoveLowerCommand;

import java.util.List;

public class ServerCommandFactory extends CommandFactory {
    @Override
    protected void addCommandClasses(List<Class<? extends Command>> commandClasses) {
        super.addCommandClasses(commandClasses);
        commandClasses.add(ClearCommand.class);
        commandClasses.add(RemoveHeadCommand.class);
        commandClasses.add(RemoveLowerCommand.class);
    }
}
