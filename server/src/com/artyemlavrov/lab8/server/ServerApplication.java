package com.artyemlavrov.lab8.server;

import com.artyemlavrov.lab8.common.DefaultServerConfig;
import com.artyemlavrov.lab8.common.application.BasicApplication;
import com.artyemlavrov.lab8.common.command.CommandFactory;
import com.artyemlavrov.lab8.common.exception.RequestFailureException;
import com.artyemlavrov.lab8.common.request.Request;
import com.artyemlavrov.lab8.common.response.Response;
import com.artyemlavrov.lab8.common.types.Authentication;
import com.artyemlavrov.lab8.server.database.AuthenticationProvider;
import com.artyemlavrov.lab8.server.database.Database;
import com.artyemlavrov.lab8.server.database.DatabaseConfig;
import com.artyemlavrov.lab8.server.requestinvoker.RequestInvoker;
import com.artyemlavrov.lab8.server.requestinvoker.RequestInvokerFactory;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.Map;

public class ServerApplication extends BasicApplication {

    private Database database;
    private Server server;

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        launch(args);
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


    @Override
    public void start(Stage stage) {
        super.start(stage);
        Map<String, String> args = getParameters().getNamed();
        int port = Integer.parseInt(args.getOrDefault("port", DefaultServerConfig.PORT));
        String databaseUrl = args.containsKey("debug") ? DatabaseConfig.URL_DEBUG : DatabaseConfig.URL;
        setAuthentication(new Authentication(DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD));
        database = new Database(databaseUrl);
        server = new Server(port, this::getResponse);
        server.start();
        setWorkersTableScene();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        server.stop();
    }

    @Override
    protected CommandFactory getCommandFactory() {
        return new ServerCommandFactory();
    }
}
