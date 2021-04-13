package com.artyemlavrov.lab6.common.types;

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

    public Worker(String name, Coordinates coordinates, double salary, Position position, Status status, Person person) {
        this.id = 0;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.ofEpochDay(0);
        this.salary = salary;
        this.position = position;
        this.status = status;
        this.person = person;
    }

    public Worker(Worker worker, Integer id, LocalDate creationDate) {
        this.id = id;
        this.name = worker.name;
        this.coordinates = worker.coordinates;
        this.creationDate = creationDate;
        this.salary = worker.salary;
        this.position = worker.position;
        this.status = worker.status;
        this.person = worker.person;
    }

    public Worker(Worker old, Worker update) {
        this(update, old.id, old.creationDate);
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
        } if (!id.equals(o.id)) {
            return id.compareTo(o.id);
        } else if (!name.equals(o.name)) {
            return name.compareTo(o.name);
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
}
