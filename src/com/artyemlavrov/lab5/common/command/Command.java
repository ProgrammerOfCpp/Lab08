package com.artyemlavrov.lab5.common.command;

import com.artyemlavrov.lab5.common.application.Application;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;

public abstract class Command<ApplicationType extends Application> {

    public abstract void execute(InterpreterLoop<ApplicationType> interpreterLoop);

    public abstract String getDescription();

    public abstract String getName();
}
