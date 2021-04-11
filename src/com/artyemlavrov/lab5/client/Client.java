package com.artyemlavrov.lab5.client;

import com.artyemlavrov.lab5.common.application.Application;
import com.artyemlavrov.lab5.common.interpreter.Interpreter;
import com.artyemlavrov.lab5.common.interpreter.InterpreterData;
import com.artyemlavrov.lab5.common.request.Request;
import com.artyemlavrov.lab5.common.response.Response;

import java.util.function.Consumer;

public class Client extends Application {

    public <ResponseType extends Response> void makeRequest(Request request, Consumer<ResponseType> onSuccess) {
        on
    }

    @Override
    protected Interpreter<Client> getInterpreter() {
        ClientCommandFactory commandFactory = new ClientCommandFactory();
        return new Interpreter<>(this, commandFactory);
    }
}
