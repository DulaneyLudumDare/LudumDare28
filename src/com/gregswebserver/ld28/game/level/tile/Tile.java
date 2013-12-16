package com.gregswebserver.ld28.game.level.tile;


import com.gregswebserver.ld28.game.level.Boundary;
import com.gregswebserver.ld28.game.level.WorldObject;
import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.util.vectors.Vector2d;

public abstract class Tile extends WorldObject {

    protected Sprite sprite;

    public Tile(Vector2d position) {
        this.position = position;
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

    public Boundary getBoundary() {
        return new Boundary(position, new Vector2d(1));
    }
}
