package com.artyemlavrov.lab5.client.command;

import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;

public class ExitCommand extends ClientCommand {

    @Override
    public void execute(InterpreterLoop<Client> interpreterLoop) {
        interpreterLoop.stop();
    }

    @Override
    public String getDescription() {
        return ": завершить программу (без сохранения в файл)";
    }

    @Override
    public String getName() {
        return "exit";
    }
}
