package com.gregswebserver.ld28.util.vectors;

public abstract class Vector<T> {

    T x;
    T y;

    public abstract double lengthSquared();

    public abstract int getQuadrant();

    public abstract int getDirection();


    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    public void setX(T x) {
        this.x = x;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean equals(Object o) {
        if (!(o instanceof Vector)) return false;
        Vector in = (Vector) o;
        return (in.getY().equals(getY()) && in.getX().equals(getX()));
    }

    public int hashCode() {
        return toString().hashCode();
    }
}


