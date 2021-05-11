package com.artyemlavrov.lab6.server;

import com.artyemlavrov.lab6.common.DefaultServerConfig;
import com.artyemlavrov.lab6.common.application.Application;
import com.artyemlavrov.lab6.common.exception.RequestFailureException;
import com.artyemlavrov.lab6.common.interpreter.Interpreter;
import com.artyemlavrov.lab6.common.request.Request;
import com.artyemlavrov.lab6.common.response.Response;
import com.artyemlavrov.lab6.common.types.Authentication;
import com.artyemlavrov.lab6.server.database.AuthenticationProvider;
import com.artyemlavrov.lab6.server.database.Database;
import com.artyemlavrov.lab6.server.database.DatabaseConfig;
import com.artyemlavrov.lab6.server.requestinvoker.RequestInvoker;
import com.artyemlavrov.lab6.server.requestinvoker.RequestInvokerFactory;

import java.io.Serializable;

public class ServerApplication extends Application {

    private final Database database;
    private final Interpreter interpreter;
    private final Server server;

    public ServerApplication(int port, String databaseUrl) {
        setAuthentication(new Authentication(DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD));
        server = new Server(port, this::getResponse);
        interpreter = new Interpreter(this, new ServerCommandFactory());
        database = new Database(databaseUrl);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        int port = DefaultServerConfig.PORT;
        String databaseUrl = DatabaseConfig.URL;
        try {
            if (args.length > 0) port = Integer.parseInt(args[0]);
            if (args.length > 1 && args[1].equals("debug")) databaseUrl = DatabaseConfig.URL_DEBUG;
        } catch (NumberFormatException e) {
            System.err.println("Неверно указаны аргументы");
        }

        ServerApplication application = new ServerApplication(port, databaseUrl);
        application.run();
    }

    public void run() {
        server.start();
        interpreter.run();
        server.stop();
    }

    @Override
    public <REQUEST extends Request> Response getResponse(REQUEST request) throws RequestFailureException {
        RequestInvokerFactory requestInvokerFactory = new RequestInvokerFactory(this);
        RequestInvoker<REQUEST> requestInvoker = requestInvokerFactory.instantiate(request);
        return requestInvoker.invoke(request);
    }

    private Serializable getResponse(Object request) {
        try {
            return getResponse((Request) request);
        } catch (RequestFailureException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public AuthenticationProvider getAuthenticationProvider() {
        return new AuthenticationProvider(database);
    }

    public Database getDatabase() {
        return database;
    }
}
