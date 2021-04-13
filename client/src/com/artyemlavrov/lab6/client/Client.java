package com.artyemlavrov.lab6.client;

import com.artyemlavrov.lab6.common.exception.RequestFailureException;

import java.io.*;
import java.net.Socket;

public class Client {

    private final int connectionAttempts = 5;

    private final String ip;
    private final int port;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public Object makeRequest(Object request) throws RequestFailureException {
        Socket socket = sendRequest(request);
        return receiveResponse(socket);
    }

    private Socket sendRequest(Object request) throws RequestFailureException {
        for (int attempt = 0; attempt < connectionAttempts; attempt++) {
            try {
                return makeAttempt(request);
            } catch (IOException e) {
                makeDelay(attempt);
            }
        }
        throw new RequestFailureException("Ошибка: превышен лимит попыток соединения с сервером.");
    }

    private Socket makeAttempt(Object request) throws IOException {
        Socket socket = new Socket(ip, port);
        OutputStream outputStream = socket.getOutputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        objectOutputStream.flush();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        outputStream.write(bytes);
        return socket;
    }

    private void makeDelay(int attempt) throws RequestFailureException {
        System.err.printf("Не удалось установить соединение с сервером, попытка %d/%d\n", attempt + 1, connectionAttempts);
        try {
            long connectionDelayMillis = 5000;
            Thread.sleep(connectionDelayMillis);
        } catch (InterruptedException e) {
            throw new RequestFailureException("Ошибка: не удаётся выполнить ождание следующей попытки соединения.");
        }
    }

    private Object receiveResponse(Socket socket) throws RequestFailureException {
        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectStream = new ObjectInputStream(inputStream);
            Object object = objectStream.readObject();
            socket.close();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            throw new RequestFailureException("Ошибка: разрыв соединения во время получения ответа.");
        }
    }
}
