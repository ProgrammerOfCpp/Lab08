package com.artyemlavrov.lab5.client;

import com.artyemlavrov.lab5.common.Serializer;
import com.artyemlavrov.lab5.common.application.Application;
import com.artyemlavrov.lab5.common.interpreter.Interpreter;
import com.artyemlavrov.lab5.common.request.Request;
import com.artyemlavrov.lab5.common.response.Response;
import com.artyemlavrov.lab5.server.Server;

public class Client extends Application {

    public <ResponseType extends Response> ResponseType makeRequest(Request request, Class<ResponseType> responseClass) {
        try {
            String requestString = Serializer.toString(request);
            String responseString = new Server().getResponseString(requestString);
            Response responseRaw = (Response)Serializer.fromString(responseString);
            return responseClass.cast(responseRaw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void run() {
        Interpreter interpreter = new Interpreter(this, new ClientCommandFactory());
        interpreter.run();
    }
}
