package com.gregswebserver.ld28.game.level;

import com.gregswebserver.ld28.util.vectors.Vector2d;

import java.util.ArrayList;

public class Boundary {

    private Vector2d origin;
    private Vector2d opposite;
    private ArrayList<Vector2d> points;

    public Boundary(Vector2d origin, Vector2d size) {
        this.origin = origin;
        this.opposite = origin.copy().add(size);
        points = new ArrayList<>();
        points.add(origin);
        points.add(origin.copy().add(size).multiply(0.5));
        points.add(origin.copy().add(new Vector2d(size.getX(), 0)));
        points.add(origin.copy().add(new Vector2d(0, size.getY())));
        points.add(opposite);
    }

    public ArrayList<Vector2d> getPoints() {
        return points;
    }

    public boolean contains(Vector2d point) {
        return (isBetween(origin.getX(), point.getX(), opposite.getX()) && isBetween(origin.getY(), point.getY(), opposite.getY()));
    }

    public boolean isBetween(double a, double b, double c) {
        return ((a >= b && b >= c) || (a <= b && b <= c));
    }

    public boolean conflicts(Boundary other) {
        for (Vector2d point : getPoints()) {
            if (other.contains(point)) return true;
        }
        for (Vector2d point : other.getPoints()) {
            if (contains(point)) return true;
        }
        return false;
    }
}
