package com.artyemlavrov.lab6.common.types;

import java.io.Serializable;

public class Location implements Serializable {
    private final Float x; //Поле не может быть null
    private final float y;
    private final Float z; //Поле не может быть null
    private final String name; //Поле может быть null

    public Location(Float x, float y, Float z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }

    public static int compare(Location a, Location b) {
        if (a == null) {
            return b == null ? 0 : -1;
        } else {
            if (b == null) {
                return 1;
            } else if (Float.compare(a.x, b.x) != 0) {
                return Float.compare(a.x, b.x);
            } else if (Float.compare(a.y, b.y) != 0) {
                return Float.compare(a.y, b.y);
            } else if (Float.compare(a.z, b.z) != 0) {
                return Float.compare(a.z, b.z);
            } else {
                if (a.name == null) {
                    return b.name == null ? 0 : -1;
                } else {
                    return a.name.compareTo(b.name);
                }
            }
        }
    }
}
