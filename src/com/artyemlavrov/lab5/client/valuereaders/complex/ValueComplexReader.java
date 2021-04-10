package com.artyemlavrov.lab5.client.valuereaders.complex;

import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.valuereaders.ValueFormatException;
import com.artyemlavrov.lab5.client.valuereaders.ValueReader;

/**
 * Класс, позволяющий определить правила чтения сложных значений.
 */
public abstract class ValueComplexReader<T> extends ValueReader<T> {
    protected ValueComplexReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    protected T parseNotNull(String argument) throws ValueFormatException {
        if (argument.equals(getTypeName())) {
            return readFields();
        } else {
            throw new ValueFormatException("Ошибка при определении типа данных!");
        }
    }

    protected abstract T readFields();

    protected abstract String getTypeName();
}
