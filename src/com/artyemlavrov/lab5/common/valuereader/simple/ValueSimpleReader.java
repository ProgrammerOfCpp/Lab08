package com.artyemlavrov.lab5.common.valuereader.simple;

import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.valuereader.ValueReader;

/**
 * Класс, позволяющий определить правила чтения простых значений.
 */
public abstract class ValueSimpleReader<T> extends ValueReader<T> {
    protected ValueSimpleReader(IOManager ioManager) {
        super(ioManager);
    }
}
