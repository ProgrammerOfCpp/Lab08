package com.artyemlavrov.lab5.common.types;

import java.io.Serializable;
import java.time.LocalDate;

public class Worker implements Comparable<Worker>, Serializable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double salary; //Значение поля должно быть больше 0
    private Position position; //Поле не может быть null
    private Status status; //Поле может быть null
    private Person person; //Поле может быть null

    public Worker(Integer id, String name, Coordinates coordinates, LocalDate creationDate, double salary, Position position, Status status, Person person) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.salary = salary;
        this.position = position;
        this.status = status;
        this.person = person;
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
