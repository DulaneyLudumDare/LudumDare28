package com.gregswebserver.ld28.graphics.screen;

import com.gregswebserver.ld28.game.level.Boundary;
import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.graphics.util.LayeredGraphic;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class ScreenObject extends LayeredGraphic {

    public ScreenObject(Vector2i position, Sprite sprite, int layer) {
        super(sprite.size, position, layer);
        this.pixels = sprite.pixels;
    }

    public Boundary getBoundary() {
        return new Boundary(position.toVector2d(), size.toVector2d());
    }
}
