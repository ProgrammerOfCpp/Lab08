package com.artyemlavrov.lab6.server;

import com.artyemlavrov.lab6.common.exception.RequestFailureException;
import com.artyemlavrov.lab6.common.DefaultServerConfig;
import com.artyemlavrov.lab6.common.application.Application;
import com.artyemlavrov.lab6.common.interpreter.Interpreter;
import com.artyemlavrov.lab6.common.request.Request;
import com.artyemlavrov.lab6.common.response.Response;
import com.artyemlavrov.lab6.server.requestinvoker.RequestInvoker;
import com.artyemlavrov.lab6.server.requestinvoker.RequestInvokerFactory;

import java.io.IOException;
import java.io.Serializable;

public class ServerApplication extends Application {

    private static final String COLLECTION_BACKUP_FILENAME = "collection_backup.json";

    private final WorkersCollection collection = new WorkersCollection();
    private final RequestInvokerFactory requestInvokerFactory = new RequestInvokerFactory();
    private final Server server;
    private final Interpreter interpreter;

    public ServerApplication(int port, String collectionFilename) {
        server = new Server(port, this::getResponse);
        interpreter = new Interpreter(this, new ServerCommandFactory());

        try {
            collection.loadFromFile(collectionFilename);
        } catch (IOException e) {
            System.out.println("Коллекция не была загружена из файла.");
        }
    }

    public static void main(String[] args) {
        int port = DefaultServerConfig.PORT;
        String collectionFilename = COLLECTION_BACKUP_FILENAME;
        try {
            if (args.length > 0) port = Integer.parseInt(args[0]);
            if (args.length > 1) collectionFilename = args[1];
        } catch (NumberFormatException e) {
            System.err.println("Неверно указаны аргументы.");
        }

        ServerApplication application = new ServerApplication(port, collectionFilename);
        application.run();
    }

    public void run() {
        addShutdownHook();
        server.start();
        interpreter.run();
        server.stop();
    }

    private void addShutdownHook() {
        Runtime runtime = Runtime.getRuntime();
        Thread hook = new Thread(() -> collection.saveToFile("collection_backup.json"));
        runtime.addShutdownHook(hook);
    }

    @Override
    public <ResponseType extends Response, RequestType extends Request> ResponseType getResponse(RequestType request) throws RequestFailureException {
        RequestInvoker<RequestType, ResponseType> requestInvoker = requestInvokerFactory.instantiate(request);
        return requestInvoker.invoke(collection, request);
    }

    private Serializable getResponse(Object request) {
        try {
            return getResponse((Request)request);
        } catch (RequestFailureException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public WorkersCollection getCollection() {
        return collection;
    }
}
