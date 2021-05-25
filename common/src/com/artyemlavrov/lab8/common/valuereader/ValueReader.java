package com.artyemlavrov.lab8.common.valuereader;

import com.artyemlavrov.lab8.common.exception.ValueFormatException;
import com.artyemlavrov.lab8.common.util.IOManager;

/**
 * Класс, позволяющмй определить правила ввода значений определённого типа данных, включая интерпретацию введённых данных и обработку ошибок.
 */
public abstract class ValueReader<T> {
    protected final IOManager ioManager;

    private boolean isNullable = true;

    protected ValueReader(IOManager ioManager) {
        this.ioManager = ioManager;
    }

    public T read() {
        return read("");
    }

    public T read(String fieldName) {
        writeFieldName(fieldName);
        T value = null;
        boolean valueRead = false;
        while (!valueRead) {
            try {
                value = readAttempt();
                valueRead = true;
            } catch (ValueFormatException e) {
                ioManager.writeError(e.getMessage());
            }
        }
        return value;
    }

    protected T readAttempt() throws ValueFormatException {
        String argument = ioManager.readNext();
        return parse(argument);
    }

    protected T parse(String argument) throws ValueFormatException {
        if (argument.isEmpty()) {
            return parseNull();
        } else {
            return parseNotNull(argument);
        }
    }

    @SuppressWarnings("SameReturnValue")
    protected T parseNull() throws ValueFormatException {
        if (!isNullable())
            throw new ValueFormatException("Это поле не может быть пустым!");
        return null;
    }

    abstract protected T parseNotNull(String argument) throws ValueFormatException;

    protected void writeFieldName(String fieldName) {
        if (!fieldName.isEmpty()) {
            ioManager.write("Введите " + fieldName + ": ");
        }
    }

    public boolean isNullable() {
        return isNullable;
    }

    /**
     * Позволяет указать, может ли читаеемое значение быть null.
     */
    public ValueReader<T> setNullable(boolean isNullable) {
        this.isNullable = isNullable;
        return this;
    }
}
