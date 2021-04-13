package com.artyemlavrov.lab6.client;

import com.artyemlavrov.lab6.common.exception.RequestFailureException;
import com.artyemlavrov.lab6.common.DefaultServerConfig;
import com.artyemlavrov.lab6.common.application.Application;
import com.artyemlavrov.lab6.common.interpreter.Interpreter;
import com.artyemlavrov.lab6.common.request.Request;
import com.artyemlavrov.lab6.common.response.Response;

public class ClientApplication extends Application {
    private Client client;

    public static void main(String[] args) {
        String ip = DefaultServerConfig.IP;
        int port = DefaultServerConfig.PORT;
        try {
            if (args.length > 0) ip = args[0];
            if (args.length > 1) port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Неверно указаны аргументы.");
        }

        ClientApplication application = new ClientApplication();
        application.run(ip, port);
    }

    public void run(String ip, int port) {
        client = new Client(ip, port);

        Interpreter interpreter = new Interpreter(this, new ClientCommandFactory());
        interpreter.run();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <ResponseType extends Response, RequestType extends Request> ResponseType getResponse(RequestType request) throws RequestFailureException {
        return (ResponseType) client.makeRequest(request);
    }
}
