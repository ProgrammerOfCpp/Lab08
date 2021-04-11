package com.artyemlavrov.lab5.client.command.interactive;

import com.artyemlavrov.lab5.common.interpreter.InterpreterData;
import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;

import java.util.List;

public class HistoryCommand extends InteractiveCommand {

    @Override
    public String getDescription() {
        return ": вывести последние 6 команд (без их аргументов)";
    }

    @Override
    public String getName() {
        return "history";
    }

    @Override
    protected void execute(InterpreterLoop<Client> interpreterLoop, IOManager ioManager) {
        InterpreterData interpreterData = interpreterLoop.getInterpreterData();
        List<String> history = interpreterData.getHistory(6);
        for (String entry : history) {
            ioManager.writeLine(entry);
        }
    }
}
