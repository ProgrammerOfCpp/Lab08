package com.artyemlavrov.lab5.client;

import com.artyemlavrov.lab5.client.command.ClientCommand;
import com.artyemlavrov.lab5.client.command.ExitCommand;
import com.artyemlavrov.lab5.client.command.interactive.ExecuteScriptCommand;
import com.artyemlavrov.lab5.client.command.interactive.HelpCommand;
import com.artyemlavrov.lab5.client.command.interactive.HistoryCommand;
import com.artyemlavrov.lab5.client.command.interactive.serverside.InfoCommand;
import com.artyemlavrov.lab5.client.command.interactive.serverside.*;
import com.artyemlavrov.lab5.common.command.CommandFactory;

public class ClientCommandFactory extends CommandFactory<ClientCommand> {

    @Override
    protected ClientCommand[] getCommandsForRegistration() {
        return new ClientCommand[] {
                new HelpCommand(),
                new HelpCommand(),
                new InfoCommand(),
                new ShowCommand(),
                new AddCommand(),
                new UpdateCommand(),
                new RemoveByIdCommand(),
                new ClearCommand(),
                new ExecuteScriptCommand(),
                new ExitCommand(),
                new RemoveHeadCommand(),
                new RemoveLowerCommand(),
                new HistoryCommand(),
                new SumOfSalaryCommand(),
                new MaxByCreationDateCommand(),
                new PrintFieldDescendingStatusCommand(),
        };
    }
}
