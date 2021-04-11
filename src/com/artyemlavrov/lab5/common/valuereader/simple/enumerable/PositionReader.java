package com.artyemlavrov.lab5.common.valuereader.simple.enumerable;

import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.types.Position;

public class PositionReader extends EnumReader<Position> {
    public PositionReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    Position[] getEnumValues() {
        return Position.values();
    }

    @Override
    Position valueOf(String name) {
        return Position.valueOf(name);
    }
}