package com.artyemlavrov.lab5.common.application;

import com.artyemlavrov.lab5.common.interpreter.Interpreter;

public abstract class Application {

    private final Interpreter<? extends Application> interpreter = getInterpreter();

    public void run() {
        interpreter.run();
    }

    protected abstract Interpreter<? extends Application> getInterpreter();
}
