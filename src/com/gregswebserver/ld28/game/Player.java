package com.gregswebserver.ld28.game;

import com.gregswebserver.ld28.graphics.screen.ScreenArea;
import com.gregswebserver.ld28.graphics.screen.ScreenObject;
import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2d;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class Player extends UsesGame {

    public double walkSpeed = 0;

    private Location location;

    public Player(Location location) {
        this.location = location;
    }

    public ScreenArea getScreenArea() {
        ScreenArea player = new ScreenArea(new Vector2i(32, 64), new Location(), 1);
        player.addObject("player", new ScreenObject(new Location(), getSprite(), 0));
        return player;
    }

    public void setMoving(int direction) {
        switch (direction) {
            case 1: //north
                location.setVel(new Vector2d(0, walkSpeed));
            case 2: //east
                location.setVel(new Vector2d(walkSpeed, 0));
            case 3: //south
                location.setVel(new Vector2d(0, -walkSpeed));
            case 4: //west
                location.setVel(new Vector2d(-walkSpeed, 0));
            case 0:
                location.setVel(new Vector2d());
        }
    }

    public Sprite getSprite() {
        switch (location.getVelocity().getDirection()) {
            //TODO add player walking sprites and animations
            case 1:
            case 2:
            case 3:
            case 4:
            default:
                return null;
        }
    }
}
