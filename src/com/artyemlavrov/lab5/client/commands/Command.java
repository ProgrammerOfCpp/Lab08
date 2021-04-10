package com.artyemlavrov.lab5.client.commands;

import com.artyemlavrov.lab5.client.Interpreter;

/**
 * Класс, позволяющий определять поведение и свойства комманд.
 */
public abstract class Command {
    public abstract void execute(Interpreter interpreter);

    public abstract String getDescription();

    public abstract String getName();
}
