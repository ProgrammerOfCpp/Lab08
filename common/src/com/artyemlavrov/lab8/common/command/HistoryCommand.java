package com.artyemlavrov.lab8.common.command;

import com.artyemlavrov.lab8.common.interpreter.InterpreterData;
import com.artyemlavrov.lab8.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab8.common.util.IOManager;

import java.util.List;

public class HistoryCommand extends Command {

    public HistoryCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

    @Override
    public void onExecute(IOManager ioManager) {
        InterpreterData interpreterData = getInterpreterData();
        List<String> history = interpreterData.getHistory(6);
        for (String entry : history) {
            ioManager.writeLine(entry);
        }
    }

    @Override
    public String getDescription() {
        return ": вывести последние 6 команд (без их аргументов)";
    }

    @Override
    public String getName() {
        return "history";
    }
}
