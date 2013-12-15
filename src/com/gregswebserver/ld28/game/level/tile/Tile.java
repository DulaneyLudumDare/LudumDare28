package com.gregswebserver.ld28.game.level.tile;


import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public abstract class Tile {

    protected Vector2i position;
    protected Sprite sprite;

    public Tile(Vector2i position) {
        this.position = position;
    }

    public Vector2i getPosition() {
        return position;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public boolean isSolid() {
        return false;
    }
}
