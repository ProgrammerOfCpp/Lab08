package com.artyemlavrov.lab8.common.valuereader.simple.number;

import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.exception.ValueFormatException;

public class DoubleReader extends NumberReader<Double> {
    public DoubleReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    Double parseValue(String argument) throws ValueFormatException {
        try {
            return Double.parseDouble(argument);
        } catch (Exception e) {
            throw new ValueFormatException("Некорректный формат числа!");
        }
    }

    @Override
    int compareValues(Double a, Double b) {
        return a.compareTo(b);
    }

    public DoubleReader setLowerBound(Double lowerBound) {
        super.setLowerBound(lowerBound);
        return this;
    }
}