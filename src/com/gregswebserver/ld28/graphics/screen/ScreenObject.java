package com.gregswebserver.ld28.graphics.screen;

import com.gregswebserver.ld28.graphics.util.LayeredGraphic;
import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.util.Location;

public class ScreenObject extends LayeredGraphic {

    public ScreenObject(Location location, Sprite sprite, int layer) {
        super(sprite.size, location, layer);
        this.pixels = sprite.pixels;
    }

    public void tick(){
        location.tick();
    }
}
