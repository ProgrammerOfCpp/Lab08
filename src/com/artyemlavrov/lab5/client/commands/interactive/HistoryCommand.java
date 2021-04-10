package com.artyemlavrov.lab5.client.commands.interactive;

import com.artyemlavrov.lab5.client.ClientCache;
import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.client.Interpreter;

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
    protected void execute(Interpreter interpreter, IOManager ioManager) {
        Client client = interpreter.getClient();
        ClientCache cache = client.getCache();
        List<String> history = cache.getHistory(6);
        for (String entry : history) {
            ioManager.writeLine(entry);
        }
    }
}
