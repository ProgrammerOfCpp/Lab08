package com.artyemlavrov.lab5.common.valuereader.simple.number;

import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.valuereader.ValueFormatException;
import com.artyemlavrov.lab5.common.valuereader.simple.ValueSimpleReader;

abstract class NumberReader<T extends Number> extends ValueSimpleReader<T> {
    T lowerBound;

    NumberReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    protected T parseNotNull(String argument) throws ValueFormatException {
        T value = parseValue(argument);
        if (lowerBound != null && compareValues(value, lowerBound) < 1) {
            throw new ValueFormatException("Данное поле не может быть меньше " + lowerBound.toString());
        }
        return value;
    }

    abstract T parseValue(String argument) throws ValueFormatException;

    abstract int compareValues(T a, T b);

    public NumberReader<T> setLowerBound(T lowerBound) {
        this.lowerBound = lowerBound;
        return this;
    }
}
