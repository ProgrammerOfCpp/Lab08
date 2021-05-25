package com.artyemlavrov.lab8.common.types;

import javafx.beans.property.SimpleObjectProperty;

import java.io.Serializable;
import java.time.LocalDate;

public class Worker implements Comparable<Worker>, Serializable {
    private final Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final double salary; //Значение поля должно быть больше 0
    private final Position position; //Поле не может быть null
    private final Status status; //Поле может быть null
    private final Person person; //Поле может быть null

    public Worker() {
        this("-", new Coordinates(0, 0), 1, Position.COOK, null, null);
    }
    public Worker(Integer id, LocalDate creationDate, String name, Coordinates coordinates, double salary, Position position, Status status, Person person) {
        this.id = id;
        this.creationDate = creationDate;
        this.name = name;
        this.coordinates = coordinates;
        this.salary = salary;
        this.position = position;
        this.status = status;
        this.person = person;
    }

    public Worker(String name, Coordinates coordinates, double salary, Position position, Status status, Person person) {
        this(
                0,
                LocalDate.ofEpochDay(0),
                name,
                coordinates,
                salary,
                position,
                status,
                person
        );
    }

    public Worker(Worker worker, Integer id) {
        this(
                id,
                worker.getCreationDate(),
                worker.getName(),
                worker.getCoordinates(),
                worker.getSalary(),
                worker.getPosition(),
                worker.getStatus(),
                worker.getPerson()
        );
    }

    public Worker(Worker worker, LocalDate creationDate) {
        this(
                worker.getId(),
                creationDate,
                worker.getName(),
                worker.getCoordinates(),
                worker.getSalary(),
                worker.getPosition(),
                worker.getStatus(),
                worker.getPerson()
        );
    }

    public Worker(Worker old, Worker worker) {
        this(
                old.getId(),
                old.getCreationDate(),
                worker.getName(),
                worker.getCoordinates(),
                worker.getSalary(),
                worker.getPosition(),
                worker.getStatus(),
                worker.getPerson()
        );
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", salary=" + salary +
                ", position=" + position +
                ", status=" + status +
                ", person=" + person +
                '}';
    }

    @Override
    public int compareTo(Worker o) {
        if (o == null) {
            return 1;
        } else if (!name.equals(o.name)) {
            return getName().compareTo(o.getName());
        } else if (Coordinates.compare(coordinates, o.coordinates) != 0) {
            return Coordinates.compare(coordinates, o.coordinates);
        } else if (!creationDate.equals(o.creationDate)) {
            return creationDate.compareTo(o.creationDate);
        } else if (Double.compare(salary, o.salary) != 0) {
            return Double.compare(salary, o.salary);
        } else if (!position.equals(o.position)) {
            return position.compareTo(o.position);
        } else if (Person.compare(person, o.person) != 0){
            return Person.compare(person, o.person);
        } if (!id.equals(o.id)) {
            return getId().compareTo(o.getId());
        } else {
            if (status == null) {
                return o.status == null ? 0 : -1;
            } else {
                return status.compareTo(o.status);
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Person getPerson() {
        return person;
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
