package com.artyemlavrov.lab6.common.types;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private final float x;
    private final double y; //Значение поля должно быть больше -268

    public Coordinates(float x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static int compare(Coordinates a, Coordinates b) {
        if (a == null) {
            return b == null ? 0 : -1;
        } else {
            if (b == null) {
                return 1;
            } else if (Double.compare(a.x, b.x) != 0) {
                return Double.compare(a.x, b.x);
            } else {
                return Double.compare(a.y, a.y);
            }
        }
    }

    public double getY() {
        return y;
    }

    public float getX() {
        return x;
    }
}
