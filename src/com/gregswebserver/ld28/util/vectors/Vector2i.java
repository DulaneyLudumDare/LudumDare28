package com.gregswebserver.ld28.util.vectors;

public class Vector2i extends Vector {

    private int x;
    private int y;

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

    public double length() {
        return (int) Math.sqrt(lengthSquared());
    }

    public Vector2i scale(double scale) {
        this.x *= scale;
        this.y *= scale;
        return this;
    }

    public Vector2i add(Vector2i in) {
        this.x += in.x;
        this.y += in.y;
        return this;
    }

    public Vector2i plus(Vector2i in) {
        return new Vector2i(this).add(in);
    }

    public Vector2i subtract(Vector2i in) {
        this.x -= in.x;
        this.y -= in.y;
        return this;
    }

    public Vector2i minus(Vector2i in) {
        return new Vector2i(this).subtract(in);
    }

    public int getQuadrant() {
        if (x > 0 && y > 0) return 1;
        if (x < 0 && y > 0) return 2;
        if (x < 0 && y < 0) return 3;
        if (x > 0 && y < 0) return 4;
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

    public double dot(Vector2i in) {
        return this.x * in.x + this.y * in.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector2d toVector2d() {
        return new Vector2d(x, y);
    }
}
