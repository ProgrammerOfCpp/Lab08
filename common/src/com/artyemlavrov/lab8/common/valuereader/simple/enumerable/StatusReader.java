package com.artyemlavrov.lab8.common.valuereader.simple.enumerable;

import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.types.Status;

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
