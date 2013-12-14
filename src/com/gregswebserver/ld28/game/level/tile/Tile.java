package com.gregswebserver.ld28.game.level.tile;


import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public abstract class Tile {

    protected Vector2i coord;
    protected Sprite sprite;
    protected int rotation;

    public Tile(Vector2i coord, Sprite sprite) {
        this.coord = coord;
        this.sprite = sprite;
    }

    public void setRotation(int dir) {
        rotation = dir;
    }

    public int getRotation() {
        return rotation;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public boolean collsion() {
        return false;
    }
}
