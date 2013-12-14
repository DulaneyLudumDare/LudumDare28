package com.gregswebserver.ld28.graphics.util;

import com.gregswebserver.ld28.game.UsesGame;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public abstract class Graphic extends UsesGame {

    public final int transparency = 0xffff00ff;

    public int[] pixels;
    public Vector2i size;

    public Graphic(Vector2i size) {
        this.size = size;
        pixels = new int[size.getX() * size.getY()];
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = transparency;
        }
    }
}
