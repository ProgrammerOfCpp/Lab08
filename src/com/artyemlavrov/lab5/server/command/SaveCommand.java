package com.artyemlavrov.lab5.server.command;

import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.valuereader.simple.StringReader;
import com.artyemlavrov.lab5.server.WorkersCollection;

public class SaveCommand extends ServerCommand {

    @Override
    public void execute(IOManager ioManager, WorkersCollection collection) {
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
