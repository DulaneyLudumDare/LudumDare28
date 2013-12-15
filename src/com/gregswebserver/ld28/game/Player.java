package com.gregswebserver.ld28.game;

import com.gregswebserver.ld28.graphics.screen.Screen;
import com.gregswebserver.ld28.graphics.screen.ScreenObject;
import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.graphics.sprite.SpriteAnimation;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2d;

public class Player extends UsesGame {

    public double walkSpeed = 1;

    private Location location;

    public Player(Location location) {
        this.location = location;
    }

    public void setMoving(int direction) {
        switch (direction) {
            case 1: //north
                location.setVel(new Vector2d(0, walkSpeed));
                break;
            case 2: //east
                location.setVel(new Vector2d(walkSpeed, 0));
                break;
            case 3: //south
                location.setVel(new Vector2d(0, -walkSpeed));
                break;
            case 4: //west
                location.setVel(new Vector2d(-walkSpeed, 0));
                break;
            case 0:
                location.setVel(new Vector2d());
        }
    }

    public Sprite getSprite() {
        if (location.isMoving()) {
            switch (location.getVelocity().getDirection()) {
                case 1:
                    return SpriteAnimation.move_up.iterator().next();
                case 2:
                    return SpriteAnimation.move_side.iterator().next();
                case 3:
                    return SpriteAnimation.move_down.iterator().next();
                case 4:
                    return SpriteAnimation.move_side.iterator().next().flip(0);
                default:
                    return Sprite.nullSprite;
            }
        } else {
            switch (location.getVelocity().getDirection()) {
                case 1:
                    return SpriteAnimation.stop_up.iterator().next();
                case 2:
                    return SpriteAnimation.stop_side.iterator().next();
                case 3:
                    return SpriteAnimation.stop_down.iterator().next();
                case 4:
                    return SpriteAnimation.stop_side.iterator().next().flip(0);
                default:
                    return Sprite.nullSprite;
            }
        }
    }

    public Location getLocation() {
        return location;
    }

    public void render(Screen screen) {
        screen.addObject("player", new ScreenObject(location.getPosition().toVector2i(), getSprite(), 3));
    }
}
