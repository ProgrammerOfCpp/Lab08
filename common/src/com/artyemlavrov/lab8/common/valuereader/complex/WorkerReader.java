package com.artyemlavrov.lab8.common.valuereader.complex;

import com.artyemlavrov.lab8.common.types.*;
import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.valuereader.simple.StringReader;
import com.artyemlavrov.lab8.common.valuereader.simple.enumerable.PositionReader;
import com.artyemlavrov.lab8.common.valuereader.simple.enumerable.StatusReader;
import com.artyemlavrov.lab8.common.valuereader.simple.number.DoubleReader;

public class WorkerReader extends ValueComplexReader<Worker> {
    public WorkerReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    protected Worker readFields() {
        String name = new StringReader(ioManager).setCanBeEmpty(false).setNullable(false).read("имя");
        Coordinates coordinates = new CoordinatesReader(ioManager).setNullable(false).read("координаты");
        double salary = new DoubleReader(ioManager).setLowerBound(0.0).setNullable(false).read("зарплату");
        Position position = new PositionReader(ioManager).setNullable(false).read("должность");
        Status status = new StatusReader(ioManager).setNullable(true).read("статус");
        Person person = new PersonReader(ioManager).setNullable(true).read("личностную информацию");
        return new Worker(
                name,
                coordinates,
                salary,
                position,
                status,
                person
        );
    }

    @Override
    protected String getTypeName() {
        return Worker.class.getSimpleName();
    }
}