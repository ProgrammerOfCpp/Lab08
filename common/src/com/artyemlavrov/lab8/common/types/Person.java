package com.artyemlavrov.lab8.common.types;

import java.io.Serializable;

public class Person implements Serializable {
    private final Integer weight; //Поле может быть null, Значение поля должно быть больше 0
    private final Country nationality; //Поле не может быть null
    private final Location location; //Поле не может быть null

    public Person(Integer weight, Country nationality, Location location) {
        this.weight = weight;
        this.nationality = nationality;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Person{" +
                "weight=" + weight +
                ", nationality=" + nationality +
                ", location=" + location +
                '}';
    }

    public Country getNationality() {
        return nationality;
    }

    public Integer getWeight() {
        return weight;
    }

    public Location getLocation() {
        return location;
    }

    public static int compare(Person a, Person b) {
        if (a == null) {
            return b == null ? 0 : -1;
        } else {
            if (b == null) {
                return 1;
            } else if (Location.compare(a.location, b.location) != 0) {
                return Location.compare(a.location, b.location);
            } else if (!a.nationality.equals(b.nationality)) {
                return a.nationality.compareTo(b.nationality);
            } else {
                return Integer.compare(a.weight, b.weight);
            }
        }
    }
}
