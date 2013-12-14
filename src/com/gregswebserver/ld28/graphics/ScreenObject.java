package com.gregswebserver.ld28.graphics;

import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class ScreenObject extends LayeredGraphic {

    public ScreenObject(Location location, Sprite sprite, int layer) {
        super(sprite.size, location, layer);
        this.pixels = sprite.pixels;
    }
}
