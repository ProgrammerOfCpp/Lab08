package com.artyemlavrov.lab5.client;

import com.artyemlavrov.lab5.request.Request;
import com.artyemlavrov.lab5.response.Response;

import java.util.function.Consumer;

public class Client {
    private final ClientCache cache = new ClientCache();

    public <ResponseType extends Response> void makeRequest(Request request, Consumer<ResponseType> onSuccess) {
        //onSuccess.apply((ResponseType));
    }

    public ClientCache getCache() {
        return cache;
    }

    public void run() {
        Interpreter interpreter = new Interpreter(this);
        interpreter.run();
    }
}
