package com.gregswebserver.ld28.graphics.util;

import com.gregswebserver.ld28.util.vectors.Vector2i;

public abstract class LayeredGraphic extends Graphic {

    protected int layer;
    protected Vector2i position;

    public LayeredGraphic(Vector2i size, Vector2i position, int layer) {
        super(size);
        this.position = position;
        this.layer = layer;
        clear();
    }

    public int getLayer() {
        return layer;
    }

    public Vector2i getPosition() {
        return position;
    }

    protected void renderImage(Vector2i position, Graphic image) {
        int ySize = image.size.getY();
        int xSize = image.size.getX();
        int yPos = position.getY();
        int xPos = position.getX();
        int height = size.getY();
        int width = size.getX();
        for (int ys = 0; ys < ySize; ys++) {
            int yp = ys + yPos;
            if (yp < 0 || yp > height - 1)
                continue;
            for (int xs = 0; xs < xSize; xs++) {
                int xp = xs + xPos;
                if (xp < 0 || xp > width - 1)
                    continue;
                int col = image.pixels[xs + (ys * xSize)];
                if (col != transparency)
                    pixels[xp + yp * width] = col;
            }
        }
    }
}
