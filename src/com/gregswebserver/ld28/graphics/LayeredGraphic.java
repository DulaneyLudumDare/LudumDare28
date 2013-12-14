package com.gregswebserver.ld28.graphics;

import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class LayeredGraphic extends Graphic {

    protected int layer;
    protected Location location;

    public LayeredGraphic(Vector2i size, Location location, int layer) {
        super(size);
        this.location = location;
        this.layer = layer;
    }

    public int getLayer() {
        return layer;
    }

    public Vector2i getPosition() {
        return location.getPosition().toVector2i();
    }

    protected void renderImage(Vector2i position, Graphic image) {
        for (int x = 0; x < image.size.getX(); x++) {
            int xx = position.getX() + x;
            for (int y = 0; y < image.size.getY(); y++) {
                int yy = position.getY() + y;
                if (xx >= position.getX() || xx < 0 || yy >= position.getY() || yy < 0)
                    continue;
                int col = image.pixels[x + (y * image.size.getX())];
                pixels[xx + (yy * size.getX())] = col;
            }
        }
    }
}
