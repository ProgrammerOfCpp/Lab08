package com.artyemlavrov.lab6.server.command;

import com.artyemlavrov.lab6.common.command.Command;
import com.artyemlavrov.lab6.common.interpreter.InterpreterLoop;
import com.artyemlavrov.lab6.common.util.IOManager;
import com.artyemlavrov.lab6.common.valuereader.simple.StringReader;
import com.artyemlavrov.lab6.server.ServerApplication;
import com.artyemlavrov.lab6.server.WorkersCollection;

public class SaveCommand extends Command {

    @Override
    public void execute(InterpreterLoop interpreterLoop, IOManager ioManager) {
        ServerApplication application = (ServerApplication)interpreterLoop.getApplication();
        WorkersCollection collection = application.getCollection();
        String filename = new StringReader(ioManager).setCanBeEmpty(false).read();
        collection.saveToFile(filename);
        ioManager.writeLine("Коллекция сохранена.");
    }

    @Override
    public String getDescription() {
        return "file_name : сохранить коллекцию в файл.";
    }

    @Override
    public String getName() {
        return "save";
    }
}
