package com.gregswebserver.ld28.game;

import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.graphics.sprite.SpriteAnimation;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2d;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class Player extends UsesGame {

    public double walkSpeed = 2;
    public int animSpeed = 6;
    private int cycleNum = 0;
    private Sprite sprite;

    private Location location;

    public Player(Location location) {
        this.location = location;
    }

    public void setMoving(Vector2i direction) {
        location.setVel(new Vector2d(direction.getX(), -direction.getY()).multiply(walkSpeed));
        if (location.getVelocity().lengthSquared() > 0.5) location.setRot(location.getVelocity());
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
        if (location.isMoving()) {
            switch (location.getRotation().getDirection()) {
                case 2:
                    return SpriteAnimation.move_right.next();
                case 3:
                    return SpriteAnimation.move_up.next();
                case 4:
                    return SpriteAnimation.move_left.next();
                default:
                    return SpriteAnimation.move_down.next();
            }
        } else {
            switch (location.getRotation().getDirection()) {
                case 2:
                    return SpriteAnimation.stop_right.next();
                case 3:
                    return SpriteAnimation.stop_up.next();
                case 4:
                    return SpriteAnimation.stop_left.next();
                default:
                    return SpriteAnimation.stop_down.next();
            }
        }
    }

    public Location getLocation() {
        return location;
    }
}
