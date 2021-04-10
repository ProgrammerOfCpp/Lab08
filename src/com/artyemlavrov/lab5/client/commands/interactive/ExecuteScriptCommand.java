package com.artyemlavrov.lab5.client.commands.interactive;

import com.artyemlavrov.lab5.client.ClientCache;
import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.Client;
import com.artyemlavrov.lab5.client.Interpreter;
import com.artyemlavrov.lab5.client.valuereaders.simple.StringReader;

public class ExecuteScriptCommand extends InteractiveCommand {

    @Override
    public String getDescription() {
        return "file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    protected void execute(Interpreter interpreter, IOManager ioManager) {
        String scriptName = new StringReader(ioManager).setCanBeEmpty(false).setNullable(false).read();
        Client client = interpreter.getClient();
        ClientCache cache = client.getCache();
        if (cache.isInScriptStack(scriptName)) {
            System.err.printf("Обнаружено зацикливание! Скрипт %s не будет запущен.\n", scriptName);
        } else {
            runScript(cache, client, scriptName);
        }
    }

    private void runScript(ClientCache cache, Client client, String scriptName) {
        cache.pushToScriptStack(scriptName);
        Interpreter newInterpreter = new Interpreter(client);
        newInterpreter.run();
        cache.popFromScriptStack();
    }
}
