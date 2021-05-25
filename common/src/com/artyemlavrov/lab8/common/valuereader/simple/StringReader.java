package com.artyemlavrov.lab8.common.valuereader.simple;

import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.exception.ValueFormatException;

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
