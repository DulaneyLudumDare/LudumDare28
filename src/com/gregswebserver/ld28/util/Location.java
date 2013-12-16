package com.gregswebserver.ld28.util;

import com.gregswebserver.ld28.util.vectors.Vector2d;

public class Location implements Tickable {

    private Vector2d rotation;
    private Vector2d rVelocity;
    private Vector2d position;
    private Vector2d velocity;
    private Vector2d acceleration;

    public Location() {
        rotation = new Vector2d();
        rVelocity = new Vector2d();
        position = new Vector2d();
        velocity = new Vector2d();
        acceleration = new Vector2d();
    }

    public Location(Vector2d position) {
        this.position = position;
        velocity = new Vector2d();
        acceleration = new Vector2d();
        rotation = new Vector2d();
        rVelocity = new Vector2d();
    }

    public void tick() {
        rotation.add(rVelocity);
        position.add(velocity);
        velocity.add(acceleration);
    }

    public void untick() {
        rotation.subtract(rVelocity);
        velocity.subtract(acceleration);
        position.subtract(velocity);
    }

    public void setRot(Vector2d in) {
        this.rotation = in;
    }

    public void setRVel(Vector2d in) {
        this.rVelocity = in;
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

    public Vector2d getRotation() {
        return rotation;
    }

    public Vector2d getRVelocity() {
        return rVelocity;
    }

    public Vector2d getPosition() {
        return position;
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public Vector2d getAcceleration() {
        return acceleration;
    }

    public boolean isMoving() {
        if (Math.abs(velocity.getX()) > 0.0000001) return false;
        if (Math.abs(velocity.getY()) > 0.0000001) return false;
        return true;
    }
}
