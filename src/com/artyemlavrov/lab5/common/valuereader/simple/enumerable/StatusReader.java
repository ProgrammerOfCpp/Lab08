package com.artyemlavrov.lab5.common.valuereader.simple.enumerable;

import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.types.Status;

public class StatusReader extends EnumReader<Status> {
    public StatusReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    Status[] getEnumValues() {
        return Status.values();
    }

    @Override
    Status valueOf(String name) {
        return Status.valueOf(name);
    }
}
