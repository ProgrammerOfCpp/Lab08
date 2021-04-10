package com.artyemlavrov.lab5.client.valuereaders.simple;

import com.artyemlavrov.lab5.client.IOManager;
import com.artyemlavrov.lab5.client.valuereaders.ValueReader;

/**
 * Класс, позволяющий определить правила чтения простых значений.
 */
public abstract class ValueSimpleReader<T> extends ValueReader<T> {
    protected ValueSimpleReader(IOManager ioManager) {
        super(ioManager);
    }
}
