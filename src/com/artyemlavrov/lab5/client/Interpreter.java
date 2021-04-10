package com.artyemlavrov.lab5.client;

import com.artyemlavrov.lab5.client.commands.*;

import java.io.IOException;

enum InterpreterMode { CONSOLE, SCRIPT }

/**
 * Класс, организующий цикл обработки команд.
 */
public class Interpreter {
    private InterpreterMode mode = InterpreterMode.CONSOLE;
    private final IOManager ioManager = new IOManager();
    private final ClientCache cache = new ClientCache();
    private final Client client;
    private boolean stopFlag = false;

    public Interpreter(Client client) {
        this.client = client;
    }

    public void openScript(String scriptPath) {
        try {
            ioManager.setInputFile(scriptPath);
            mode = InterpreterMode.SCRIPT;
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
            stop();
        }
    }

    /**
     * Метод, запускающий основной цикл интерпретатора.
     */
    public void run() {
        while (shouldContinue()) {
            readAndExecuteCommand();
        }
    }

    private boolean shouldContinue() {
        if (stopFlag) {
            return false;
        }
        switch (mode) {
            case CONSOLE:
                return true;
            case SCRIPT:
                return ioManager.hasNext();
        }
        return false;
    }

    private void readAndExecuteCommand() {
        Command command = readCommand();
        if (command == null) {
            ioManager.writeLine("Команда с указанным именем не найдена!");
        } else {
            command.execute(this);
            cache.addToHistory(command.getName());
            ioManager.writeLine("");
        }
    }

    /**
     * Метод, осуществляющий чтение очередной комманды.
     * @return Ссылка на экземпляр распознанной комманды.
     */
    private Command readCommand() {
        String name;
        do {
            name = ioManager.readNext().trim();
        } while (name.isEmpty());
        CommandFactory commandFactory = CommandFactory.getInstance();
        return commandFactory.instantiate(name);
    }

    /**
     * Метод, прекращающий работу интерпретатора.
     */
    public void stop() {
        stopFlag = true;
    }

    public IOManager getIOManager() {
        return ioManager;
    }

    public Client getClient() {
        return client;
    }
}
