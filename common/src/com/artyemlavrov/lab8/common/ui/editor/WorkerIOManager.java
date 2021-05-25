package com.artyemlavrov.lab8.common.ui.editor;

import com.artyemlavrov.lab8.common.types.Country;
import com.artyemlavrov.lab8.common.types.Position;
import com.artyemlavrov.lab8.common.types.Status;
import com.artyemlavrov.lab8.common.util.IOManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WorkerIOManager extends IOManager {

    private final String name;
    private final String coordinatesX, coordinatesY;
    private final String salary;
    private final Position position;
    private final boolean hasStatus;
    private final Status status;
    private final boolean hasPerson;
    private final boolean hasWeight;
    private final String weight;
    private final Country nationality;
    private final String locationX, locationY, locationZ;
    private final boolean hasLocationName;
    private final String locationName;

    private final List<String> tokens = new ArrayList<>();
    private Iterator<String> tokensIterator;

    public WorkerIOManager(String name, String coordinatesX, String coordinatesY, String salary, Position position, boolean hasStatus, Status status, boolean hasPerson, boolean hasWeight, String weight, Country nationality, String locationX, String locationY, String locationZ, boolean hasLocationName, String locationName) {
        this.name = name;
        this.coordinatesX = coordinatesX;
        this.coordinatesY = coordinatesY;
        this.salary = salary;
        this.position = position;
        this.hasStatus = hasStatus;
        this.status = status;
        this.hasPerson = hasPerson;
        this.hasWeight = hasWeight;
        this.weight = weight;
        this.nationality = nationality;
        this.locationX = locationX;
        this.locationY = locationY;
        this.locationZ = locationZ;
        this.hasLocationName = hasLocationName;
        this.locationName = locationName;
        buildTokens();
    }

    private void buildTokens() {
        addNext("Worker");
        addNext(name);
        addNext("Coordinates");
        addNext(coordinatesX);
        addNext(coordinatesY);
        addNext(salary);
        addNext(position.toString());
        addNext(hasStatus ? status.toString() : null);
        if (hasPerson) {
            addNext("Person");
            addNext(hasWeight ? weight : null);
            addNext(nationality.toString());
            addNext("Location");
            addNext(hasLocationName ? locationName : null);
            addNext(locationX);
            addNext(locationY);
            addNext(locationZ);
        } else {
            addNext(null);
        }
    }

    private void addNext(String value) {
        if (value == null) {
            addNext("");
        } else {
            tokens.add(value);
        }
    }

    @Override
    public void writeError(Object o) {
        throw new InvalidWorkerException(o.toString());
    }

    @Override
    public void write(Object o) { }

    @Override
    public String readNext() {
        if (tokensIterator == null) {
            tokensIterator = tokens.iterator();
        }
        return tokensIterator.next();
    }
}
