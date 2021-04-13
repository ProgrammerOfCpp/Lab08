package com.artyemlavrov.lab6.common.valuereader.simple;

import com.artyemlavrov.lab6.common.util.IOManager;
import com.artyemlavrov.lab6.common.exception.ValueFormatException;

public class StringReader extends ValueSimpleReader<String> {
    boolean canBeEmpty = true;

    public StringReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    protected String parseNotNull(String argument) throws ValueFormatException {
        if (argument.trim().isEmpty() && !canBeEmpty()) {
            throw new ValueFormatException("Эта строка не может быть пустой!");
        }
        return argument;
    }

    boolean canBeEmpty() {
        return canBeEmpty;
    }

    public StringReader setCanBeEmpty(boolean canBeEmpty) {
        this.canBeEmpty = canBeEmpty;
        return this;
    }
}
