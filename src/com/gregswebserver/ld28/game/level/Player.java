package com.gregswebserver.ld28.game.level;

import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.graphics.sprite.SpriteAnimation;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2d;

public class Player extends WorldObject {

    private SpriteAnimation stop_right = new SpriteAnimation().addSprite(Sprite.player_stop_right_0).addSprite(Sprite.player_stop_right_1).addSprite(Sprite.player_stop_right_2).addSprite(Sprite.player_stop_right_3);
    private SpriteAnimation stop_left = new SpriteAnimation().addSprite(Sprite.player_stop_left_0).addSprite(Sprite.player_stop_left_1).addSprite(Sprite.player_stop_left_2).addSprite(Sprite.player_stop_left_3);
    private SpriteAnimation stop_up = new SpriteAnimation().addSprite(Sprite.player_stop_up_0).addSprite(Sprite.player_stop_up_1).addSprite(Sprite.player_stop_up_2).addSprite(Sprite.player_stop_up_3);
    private SpriteAnimation stop_down = new SpriteAnimation().addSprite(Sprite.player_stop_down_0).addSprite(Sprite.player_stop_down_1).addSprite(Sprite.player_stop_down_2).addSprite(Sprite.player_stop_down_3);
    private SpriteAnimation move_right = new SpriteAnimation().addSprite(Sprite.player_move_right_0).addSprite(Sprite.player_move_right_1).addSprite(Sprite.player_move_right_2).addSprite(Sprite.player_move_right_3);
    private SpriteAnimation move_left = new SpriteAnimation().addSprite(Sprite.player_move_left_0).addSprite(Sprite.player_move_left_1).addSprite(Sprite.player_move_left_2).addSprite(Sprite.player_move_left_3);
    private SpriteAnimation move_up = new SpriteAnimation().addSprite(Sprite.player_move_up_0).addSprite(Sprite.player_move_up_1).addSprite(Sprite.player_move_up_2).addSprite(Sprite.player_move_up_3);
    private SpriteAnimation move_down = new SpriteAnimation().addSprite(Sprite.player_move_down_0).addSprite(Sprite.player_move_down_1).addSprite(Sprite.player_move_down_2).addSprite(Sprite.player_move_down_3);


    public double walkSpeed = .1;
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
        int rotation = location.getRotation().getDirection();
        if (rotation == 0) rotation = location.getRotation().getQuadrant();

        if (location.isMoving()) {
            switch (rotation) {
                case 2:
                    return move_right.next();
                case 3:
                    return move_up.next();
                case 4:
                    return move_left.next();
                default:
                    return move_down.next();
            }
        } else {
            switch (rotation) {
                case 2:
                    return stop_right.next();
                case 3:
                    return stop_up.next();
                case 4:
                    return stop_left.next();
                default:
                    return stop_down.next();
            }
        }
    }

    public Location getLocation() {
        return location;
    }

    public Boundary getBoundary() {
        return new Boundary(location.getPosition().copy().add(new Vector2d(.2, 1.2)), new Vector2d(0.6));
    }

    public boolean isSolid() {
        return true;
    }
}
