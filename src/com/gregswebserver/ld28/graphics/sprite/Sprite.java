package com.gregswebserver.ld28.graphics.sprite;

import com.gregswebserver.ld28.graphics.util.Graphic;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class Sprite extends Graphic {

    public Sprite() {
        super(new Vector2i());
        clear();
    }

    public Sprite(Vector2i pos, int size, SpriteSheet spritesheet) {
        // Single-size square sprites, arranged on a grid on the sheet.
        super(new Vector2i(size));
        clear();
        for (int x = 0; x < size; x++) {
            int xx = pos.getX() * size + x;
            for (int y = 0; y < size; y++) {
                int yy = pos.getY() * size + y;
                int col = spritesheet.pixels[xx + (yy * spritesheet.size.getX())];
                pixels[x + y * size] = col;
            }
        }
    }

    public Sprite(Vector2i start, Vector2i dim, SpriteSheet spritesheet) {
        //Dynamic sprites with varying sizes and start positions.
        super(dim);
        clear();
        if (start.getQuadrant() == 1 && spritesheet.size.contains(start.plus(dim))) {
            for (int y = 0; y < dim.getY(); y++) {
                for (int x = 0; x < dim.getX(); x++) {
                    int col = spritesheet.pixels[start.getX() + x + (start.getY() + y) * spritesheet.size.getX()];
                    pixels[x + y * dim.getX()] = col;
                }
            }
        }
    }
}
