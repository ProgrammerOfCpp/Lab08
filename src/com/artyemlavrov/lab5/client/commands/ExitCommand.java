package com.artyemlavrov.lab5.client.commands;

import com.artyemlavrov.lab5.client.Interpreter;

public class ExitCommand extends Command {
    @Override
    public void execute(Interpreter interpreter) {
        interpreter.stop();
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
