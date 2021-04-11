package com.artyemlavrov.lab5.common.command;

import com.artyemlavrov.lab5.common.interpreter.Interpreter;
import com.artyemlavrov.lab5.common.interpreter.InterpreterData;
import com.artyemlavrov.lab5.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.valuereader.simple.StringReader;

public class ExecuteScriptCommand extends Command {

    @Override
    public String getDescription() {
        return "file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public void execute(InterpreterLoop interpreterLoop, IOManager ioManager) {
        String scriptName = new StringReader(ioManager).setCanBeEmpty(false).setNullable(false).read();
        Interpreter interpreter = interpreterLoop.getInterpreter();
        InterpreterData interpreterData = interpreterLoop.getInterpreterData();
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
