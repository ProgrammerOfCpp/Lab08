package com.artyemlavrov.lab6.common.valuereader.simple.number;

import com.artyemlavrov.lab6.common.util.IOManager;
import com.artyemlavrov.lab6.common.exception.ValueFormatException;

public class IntegerReader extends NumberReader<Integer> {
    public IntegerReader(IOManager ioManager) {
        super(ioManager);
        this.setNullable(true);
    }

    @Override
    Integer parseValue(String argument) throws ValueFormatException {
        try {
            return Integer.parseInt(argument);
        } catch (Exception e) {
            throw new ValueFormatException("Некорректный формат числа!");
        }
    }

    public IntegerReader setLowerBound(Integer lowerBound) {
        super.setLowerBound(lowerBound);
        return this;
    }

    @Override
    int compareValues(Integer a, Integer b) {
        return a.compareTo(b);
    }
}
