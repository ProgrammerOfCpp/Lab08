package com.artyemlavrov.lab8.common.valuereader.simple.enumerable;

import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.types.Position;

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