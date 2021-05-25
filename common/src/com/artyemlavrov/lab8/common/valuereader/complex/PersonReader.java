package com.artyemlavrov.lab8.common.valuereader.complex;

import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.types.Country;
import com.artyemlavrov.lab8.common.types.Location;
import com.artyemlavrov.lab8.common.types.Person;
import com.artyemlavrov.lab8.common.valuereader.simple.enumerable.CountryReader;
import com.artyemlavrov.lab8.common.valuereader.simple.number.IntegerReader;

public class PersonReader extends ValueComplexReader<Person> {
    public PersonReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    protected Person readFields() {
        Integer weight = new IntegerReader(ioManager).setLowerBound(0).setNullable(true).read("вес");
        Country nationality = new CountryReader(ioManager).setNullable(false).read("национальность");
        Location location = new LocationReader(ioManager).setNullable(false).read("местоположение");
        return new Person(weight, nationality, location);
    }

    @Override
    protected String getTypeName() {
        return Person.class.getSimpleName();
    }
}