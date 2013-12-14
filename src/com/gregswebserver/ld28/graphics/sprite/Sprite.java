package com.gregswebserver.ld28.graphics.sprite;

import com.gregswebserver.ld28.graphics.util.Graphic;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class Sprite extends Graphic {

    //terrain sprites
    public static Sprite path_flat = new Sprite(new Vector2i(0,0), 32, SpriteSheet.terrain);
    public static Sprite path_straight = new Sprite(new Vector2i(0,1), 32, SpriteSheet.terrain);
    public static Sprite path_corner_out = new Sprite(new Vector2i(0,2), 32, SpriteSheet.terrain);
    public static Sprite path_corner_in = new Sprite(new Vector2i(0,3), 32, SpriteSheet.terrain);
    public static Sprite wall_flat = new Sprite(new Vector2i(1,0), 32, SpriteSheet.terrain);
    public static Sprite wall_straight = new Sprite(new Vector2i(1,1), 32, SpriteSheet.terrain);
    public static Sprite wall_corner_out = new Sprite(new Vector2i(1,2), 32, SpriteSheet.terrain);
    public static Sprite wall_corner_in = new Sprite(new Vector2i(1,3), 32, SpriteSheet.terrain);

    //landmark sprites
    public static Sprite landmark_wall = new Sprite(new Vector2i(0,0), 32, SpriteSheet.landmarks);
    public static Sprite landmark_path = new Sprite(new Vector2i(0,0), 32, SpriteSheet.landmarks);

    public Sprite() {
        super(new Vector2i());
        clear();
    }

    public Sprite(Vector2i pos, int size, SpriteSheet spritesheet) {
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
