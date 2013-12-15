package com.gregswebserver.ld28.util.vectors;

public class Vector2i extends Vector<Integer> {

    public Vector2i() {
        x = y = 0;
    }

    public Vector2i(int s) {
        this.x = s;
        this.y = s;
    }

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2i(Vector2i original) {
        this.x = original.x;
        this.y = original.y;
    }

    public double lengthSquared() {
        return x * x + y * y;
    }

    public Vector2i scale(int scale) {
        this.x *= scale;
        this.y *= scale;
        return this;
    }

    public Vector2i add(Vector2i in) {
        this.x += in.x;
        this.y += in.y;
        return this;
    }

    public Vector2i subtract(Vector2i in) {
        this.x -= in.x;
        this.y -= in.y;
        return this;
    }

    public Vector2i rotate(double degrees) {
        double rads = degrees * Math.PI / 180;
        this.x = (int) ((x * Math.cos(rads)) - (y * Math.sin(rads)));
        this.y = (int) ((y * Math.cos(rads)) + (x * Math.sin(rads)));
        return this;
    }

    public Vector2i flip() {
        int a = x;
        this.x = y;
        this.y = a;
        return this;
    }

    public double dot(Vector2i in) {
        return this.x * in.x + this.y * in.y;
    }

    public Vector2d toVector2d() {
        return new Vector2d(x, y);
    }

    public int getQuadrant() {
        if (x > 0 && y > 0) return 1;
        if (x < 0 && y > 0) return 2;
        if (x < 0 && y < 0) return 3;
        if (x > 0 && y < 0) return 4;
        return 0;
    }

    public int getDirection() {
        if (x == 0 && y > 0) return 1;
        if (y == 0 && x > 0) return 2;
        if (x == 0 && y < 0) return 3;
        if (y == 0 && x < 0) return 4;
        return 0;
    }

    public boolean contains(Vector2i in) {
        if (getQuadrant() != in.getQuadrant()) return false;
        switch (in.getQuadrant()) {
            case 1:
                return getY() > in.getY() && getX() > in.getX();
            case 2:
                return getY() > in.getY() && getX() < in.getX();
            case 3:
                return getY() < in.getY() && getX() < in.getX();
            case 4:
                return getY() < in.getY() && getX() > in.getX();
            default:
                return false;
        }
    }
}
