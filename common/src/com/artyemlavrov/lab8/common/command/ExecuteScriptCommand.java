package com.artyemlavrov.lab8.common.command;

import com.artyemlavrov.lab8.common.interpreter.Interpreter;
import com.artyemlavrov.lab8.common.interpreter.InterpreterData;
import com.artyemlavrov.lab8.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.valuereader.simple.StringReader;

public class ExecuteScriptCommand extends Command {

    public ExecuteScriptCommand(InterpreterLoop interpreterLoop) {
        super(interpreterLoop);
    }

    @Override
    public String getDescription() {
        return "file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public void onExecute(IOManager ioManager) {
        String scriptName = new StringReader(ioManager).setCanBeEmpty(false).setNullable(false).read();

        Interpreter interpreter = getInterpreter();
        InterpreterData interpreterData = getInterpreterData();
        if (interpreterData.isInScriptStack(scriptName)) {
            System.err.printf("Обнаружено зацикливание! Скрипт %s не будет запущен.\n", scriptName);
        } else {
            runScript(interpreterData, interpreter, scriptName);
        }
    }

    private void runScript(InterpreterData interpreterData, Interpreter interpreter, String scriptName) {
        interpreterData.pushToScriptStack(scriptName);
        InterpreterLoop newInterpreterLoop = new InterpreterLoop(interpreter);
        newInterpreterLoop.runFromScript(scriptName);
        interpreterData.popFromScriptStack();
    }
}
