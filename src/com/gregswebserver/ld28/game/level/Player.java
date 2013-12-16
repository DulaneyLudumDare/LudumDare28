package com.gregswebserver.ld28.game.level;

import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.graphics.sprite.SpriteAnimation;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2d;

public class Player extends WorldObject {

    private SpriteAnimation stop_straight = new SpriteAnimation().addSprite(Sprite.player_stop_straight_0).addSprite(Sprite.player_stop_straight_1).addSprite(Sprite.player_stop_straight_2).addSprite(Sprite.player_stop_straight_3);
    private SpriteAnimation stop_diagonal = new SpriteAnimation().addSprite(Sprite.player_stop_diagonal_0).addSprite(Sprite.player_stop_diagonal_1).addSprite(Sprite.player_stop_diagonal_2).addSprite(Sprite.player_stop_diagonal_3);
    private SpriteAnimation move_straight = new SpriteAnimation().addSprite(Sprite.player_move_straight_0).addSprite(Sprite.player_move_straight_1).addSprite(Sprite.player_move_straight_2).addSprite(Sprite.player_move_straight_3);
    private SpriteAnimation move_diagonal = new SpriteAnimation().addSprite(Sprite.player_move_diagonal_0).addSprite(Sprite.player_move_diagonal_1).addSprite(Sprite.player_move_diagonal_2).addSprite(Sprite.player_move_diagonal_3);

    public double walkSpeed = 1.0/8;
    public int animSpeed = 6;
    private int cycleNum = 0;
    private Sprite sprite;

    private Location location;

    public Player(Location location) {
        this.location = location;
    }

    public void setMoving(Vector2d direction) {
        if (direction.lengthSquared() > walkSpeed * walkSpeed) {
            location.setVel(new Vector2d(direction.getX(), -direction.getY()).unit().multiply(walkSpeed));
            location.setRot(location.getVelocity());
        } else {
            location.setVel(new Vector2d());
        }
    }

    public Sprite getSprite() {
        cycleNum++;
        if (cycleNum == animSpeed || sprite == null) {
            cycleNum = 0;
            return sprite = getNewSprite();
        } else {
            return sprite;
        }
    }

    public Sprite getNewSprite() {
        int direction = location.getRotation().getDirection();
        int rotation = location.getRotation().getQuadrant();
        if (location.isMoving()) {
            if (direction != 0) {
                return move_straight.next().rotate(7 - direction);
            } else {
                return move_diagonal.next().rotate(rotation);
            }
        } else {
            if (direction != 0) {
                return stop_straight.next().rotate(7 - direction);
            } else {
                return stop_diagonal.next().rotate(rotation);
            }
        }
    }

    public Location getLocation() {
        return location;
    }

    public Boundary getBoundary() {
        return new Boundary(location.getPosition().copy().add(new Vector2d(.2, .2)), new Vector2d(0.6));
    }

    public Boundary getNextBoundary() {
        Location newLoc = location.copy();
        return new Boundary(newLoc.tick().getPosition().copy().add(new Vector2d(0.2, 0.2)), new Vector2d(0.6));
    }

    public boolean isSolid() {
        return true;
    }
}
