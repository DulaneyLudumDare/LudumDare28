package com.gregswebserver.ld28.graphics.util;

import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public abstract class LayeredGraphic extends Graphic {

    protected int layer;
    protected Location location;

    public LayeredGraphic(Vector2i size, Location location, int layer) {
        super(size);
        this.location = location;
        this.layer = layer;
        clear();
    }

    public int getLayer() {
        return layer;
    }

    public Location getLocation() {
        return location;
    }

    protected void renderImage(Vector2i position, Graphic image) {
        for (int x = 0; x < image.size.getX(); x++) {
            for (int y = 0; y < image.size.getY(); y++) {
                Vector2i thisPixel = new Vector2i(x, y);
                Vector2i newPixel = new Vector2i(thisPixel).add(position);
                int color = image.getPixel(thisPixel);
                if (color != transparency) {
                    if (new Vector2i(this.size).subtract(new Vector2i(1, 1)).contains(newPixel))
                        setPixel(newPixel, color);
                }
            }
        }
    }
}
