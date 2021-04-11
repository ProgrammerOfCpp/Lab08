package com.artyemlavrov.lab5.common.valuereader.complex;

import com.artyemlavrov.lab5.common.io.IOManager;
import com.artyemlavrov.lab5.common.types.*;
import com.artyemlavrov.lab5.common.valuereader.simple.StringReader;
import com.artyemlavrov.lab5.common.valuereader.simple.enumerable.PositionReader;
import com.artyemlavrov.lab5.common.valuereader.simple.enumerable.StatusReader;
import com.artyemlavrov.lab5.common.valuereader.simple.number.DoubleReader;

import java.time.LocalDate;

public class WorkerReader extends ValueComplexReader<Worker> {
    public WorkerReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    protected Worker readFields() {
        Integer id = (int)(System.currentTimeMillis() % Integer.MAX_VALUE);
        String name = new StringReader(ioManager).setCanBeEmpty(false).setNullable(false).read("имя");
        Coordinates coordinates = new CoordinatesReader(ioManager).setNullable(false).read("координаты");
        LocalDate creationDate =  LocalDate.now();
        double salary = new DoubleReader(ioManager).setLowerBound(0.0).setNullable(false).read("зарплату");
        Position position = new PositionReader(ioManager).setNullable(false).read("должность");
        Status status = new StatusReader(ioManager).setNullable(true).read("статус");
        Person person = new PersonReader(ioManager).setNullable(true).read("личностную информацию");
        return new Worker(id, name, coordinates, creationDate, salary, position, status, person);
    }

    @Override
    protected String getTypeName() {
        return Worker.class.getSimpleName();
    }
}