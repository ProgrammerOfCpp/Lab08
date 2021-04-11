package com.artyemlavrov.lab5.server;

import com.artyemlavrov.lab5.common.Serializer;
import com.artyemlavrov.lab5.common.application.Application;
import com.artyemlavrov.lab5.common.interpreter.Interpreter;
import com.artyemlavrov.lab5.common.request.Request;
import com.artyemlavrov.lab5.common.response.Response;
import com.artyemlavrov.lab5.server.requestinvoker.RequestInvoker;
import com.artyemlavrov.lab5.server.requestinvoker.UnknownRequestException;

public class Server extends Application {

    private final WorkersCollection collection = new WorkersCollection();
    private final RequestInvokerFactory requestInvokerFactory = new RequestInvokerFactory();

    public String getResponseString(String requestString) throws Exception {
        Request request = (Request)Serializer.fromString(requestString);
        Response response = invokeRequest(request, Response.class);
        return Serializer.toString(response);
    }

    public <ResponseType extends Response> ResponseType invokeRequest(Request request, Class<ResponseType> responseClass) {
        try {
            RequestInvoker<? extends Request, ? extends Response> requestInvoker = requestInvokerFactory.instantiate(request);
            Response responseRaw = requestInvoker.invoke(collection, request);
            return responseClass.cast(responseRaw);
        } catch (UnknownRequestException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public WorkersCollection getCollection() {
        return collection;
    }

    public void run(String[] args) {
        Interpreter interpreter = new Interpreter(this, new ServerCommandFactory());
        if (args.length > 0) {
            collection.loadFromFile(args[0]);
        }
        interpreter.run();
    }
}
