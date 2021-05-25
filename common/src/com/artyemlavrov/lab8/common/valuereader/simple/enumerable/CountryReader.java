package com.artyemlavrov.lab8.common.valuereader.simple.enumerable;

import com.artyemlavrov.lab8.common.util.IOManager;
import com.artyemlavrov.lab8.common.types.Country;

public class CountryReader extends EnumReader<Country> {
    public CountryReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    Country[] getEnumValues() {
        return Country.values();
    }

    @Override
    Country valueOf(String name) {
        return Country.valueOf(name);
    }
}