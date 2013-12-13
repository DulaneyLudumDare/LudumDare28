package com.gregswebserver.ld28.util;

import com.gregswebserver.ld28.util.vectors.Vector2d;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class Location implements Tickable {

    private Vector2d position;
    private Vector2d velocity;
    private Vector2d acceleration;

    public Location() {
        position = new Vector2d();
        velocity = new Vector2d();
        acceleration = new Vector2d();
    }

    public Location(Vector2d position) {
        this.position = position;
        velocity = new Vector2d();
        acceleration = new Vector2d();
    }

    public Location(Vector2d position, Vector2d velocity) {
        this.position = position;
        this.velocity = velocity;
        acceleration = new Vector2d();
    }

    public Location(Vector2d position, Vector2d velocity, Vector2d acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public void tick() {
        position.add(velocity);
        velocity.add(acceleration);
    }

    public void setPos(Vector2d in) {
        this.position = in;
    }

    public void setVel(Vector2d in) {
        this.velocity = in;
    }

    public void setAcc(Vector2d in) {
        this.acceleration = in;
    }

    public Vector2i getPosition() {
        return position.toVector2i();
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public Vector2d getAcceleration() {
        return acceleration;
    }

    public int getX() {
        return (int) position.getX();
    }

    public int getY() {
        return (int) position.getY();
    }
}
