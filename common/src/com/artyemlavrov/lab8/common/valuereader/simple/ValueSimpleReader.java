package com.artyemlavrov.lab8.common.valuereader.simple;

import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.valuereader.ValueReader;

/**
 * Класс, позволяющий определить правила чтения простых значений.
 */
public abstract class ValueSimpleReader<T> extends ValueReader<T> {
    protected ValueSimpleReader(IOManager ioManager) {
        super(ioManager);
    }
}
