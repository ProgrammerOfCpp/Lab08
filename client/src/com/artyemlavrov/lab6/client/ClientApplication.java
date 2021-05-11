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

        class Kek {
            synchronized void work() {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized void complete() {
                notifyAll();
            }
        }

        Kek kek = new Kek();
        Thread t1 = new Thread(() -> {
            kek.work();
            System.out.println("T1!");
        });
        Thread t2 = new Thread(() -> {
            kek.work();
            System.out.println("T2!");
        });
        t1.start();
        t2.start();

        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                kek.complete();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t3.start();



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

    @Override
    public <RequestType extends Request> Response getResponse(RequestType request) throws RequestFailureException {
        return (Response) client.makeRequest(request);
    }
}
