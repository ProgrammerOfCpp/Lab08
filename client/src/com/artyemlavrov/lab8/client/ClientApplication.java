package com.artyemlavrov.lab8.client;

import com.artyemlavrov.lab8.common.DefaultServerConfig;
import com.artyemlavrov.lab8.common.application.BasicApplication;
import com.artyemlavrov.lab8.common.command.CommandFactory;
import com.artyemlavrov.lab8.common.exception.RequestFailureException;
import com.artyemlavrov.lab8.common.request.Request;
import com.artyemlavrov.lab8.common.response.Response;
import com.artyemlavrov.lab8.common.types.Authentication;
import javafx.stage.Stage;

import java.util.Map;

public class ClientApplication extends BasicApplication {

    private Client client;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public <RequestType extends Request> Response getResponse(RequestType request) throws RequestFailureException {
        return (Response) client.makeRequest(request);
    }

    @Override
    public void start(Stage stage) {
        super.start(stage);
        Map<String, String> args = getParameters().getNamed();
        String ip = args.getOrDefault("ip", DefaultServerConfig.IP);
        int port = Integer.parseInt(args.getOrDefault("port", DefaultServerConfig.PORT));
        client = new Client(ip, port);
        setAuthentication(new Authentication("adam", "smith"));
        setWorkersTableScene();
    }

    @Override
    protected CommandFactory getCommandFactory() {
        return new ClientCommandFactory();
    }
}
