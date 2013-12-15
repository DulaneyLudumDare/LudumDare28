package com.gregswebserver.ld28.graphics.sprite;

import com.gregswebserver.ld28.graphics.util.Graphic;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class Sprite extends Graphic {

    //terrain sprites
    public static Sprite path_flat = new Sprite(new Vector2i(0, 0), 32, SpriteSheet.terrain);
    public static Sprite path_straight = new Sprite(new Vector2i(0, 1), 32, SpriteSheet.terrain);
    public static Sprite path_corner_out = new Sprite(new Vector2i(0, 2), 32, SpriteSheet.terrain);
    public static Sprite path_corner_in = new Sprite(new Vector2i(0, 3), 32, SpriteSheet.terrain);
    public static Sprite wall_flat = new Sprite(new Vector2i(1, 0), 32, SpriteSheet.terrain);
    public static Sprite wall_straight = new Sprite(new Vector2i(1, 1), 32, SpriteSheet.terrain);
    public static Sprite wall_corner_out = new Sprite(new Vector2i(1, 2), 32, SpriteSheet.terrain);
    public static Sprite wall_corner_in = new Sprite(new Vector2i(1, 3), 32, SpriteSheet.terrain);

    //landmark sprites
    public static Sprite landmark_wall = new Sprite(new Vector2i(0, 0), 32, SpriteSheet.landmarks);
    public static Sprite landmark_path = new Sprite(new Vector2i(0, 0), 32, SpriteSheet.landmarks);

    //player sprites
    public static Vector2i playerSize = new Vector2i(32, 64);

    public static Sprite player_stop_side_0 = new Sprite(new Vector2i(0, 0), playerSize, SpriteSheet.player);
    public static Sprite player_stop_side_1 = new Sprite(new Vector2i(32, 0), playerSize, SpriteSheet.player);
    public static Sprite player_stop_side_2 = new Sprite(new Vector2i(64, 0), playerSize, SpriteSheet.player);
    public static Sprite player_stop_side_3 = new Sprite(new Vector2i(96, 0), playerSize, SpriteSheet.player);
    public static Sprite player_stop_up_0 = new Sprite(new Vector2i(0, 64), playerSize, SpriteSheet.player);
    public static Sprite player_stop_up_1 = new Sprite(new Vector2i(32, 64), playerSize, SpriteSheet.player);
    public static Sprite player_stop_up_2 = new Sprite(new Vector2i(64, 64), playerSize, SpriteSheet.player);
    public static Sprite player_stop_up_3 = new Sprite(new Vector2i(96, 64), playerSize, SpriteSheet.player);
    public static Sprite player_stop_down_0 = new Sprite(new Vector2i(0, 128), playerSize, SpriteSheet.player);
    public static Sprite player_stop_down_1 = new Sprite(new Vector2i(32, 128), playerSize, SpriteSheet.player);
    public static Sprite player_stop_down_2 = new Sprite(new Vector2i(64, 128), playerSize, SpriteSheet.player);
    public static Sprite player_stop_down_3 = new Sprite(new Vector2i(96, 128), playerSize, SpriteSheet.player);

    public static Sprite player_move_side_0 = new Sprite(new Vector2i(128, 0), playerSize, SpriteSheet.player);
    public static Sprite player_move_side_1 = new Sprite(new Vector2i(160, 0), playerSize, SpriteSheet.player);
    public static Sprite player_move_side_2 = new Sprite(new Vector2i(192, 0), playerSize, SpriteSheet.player);
    public static Sprite player_move_side_3 = new Sprite(new Vector2i(224, 0), playerSize, SpriteSheet.player);
    public static Sprite player_move_up_0 = new Sprite(new Vector2i(128, 64), playerSize, SpriteSheet.player);
    public static Sprite player_move_up_1 = new Sprite(new Vector2i(160, 64), playerSize, SpriteSheet.player);
    public static Sprite player_move_up_2 = new Sprite(new Vector2i(192, 64), playerSize, SpriteSheet.player);
    public static Sprite player_move_up_3 = new Sprite(new Vector2i(224, 64), playerSize, SpriteSheet.player);
    public static Sprite player_move_down_0 = new Sprite(new Vector2i(128, 128), playerSize, SpriteSheet.player);
    public static Sprite player_move_down_1 = new Sprite(new Vector2i(160, 128), playerSize, SpriteSheet.player);
    public static Sprite player_move_down_2 = new Sprite(new Vector2i(192, 128), playerSize, SpriteSheet.player);
    public static Sprite player_move_down_3 = new Sprite(new Vector2i(224, 128), playerSize, SpriteSheet.player);

    public Sprite() {
        super(new Vector2i());
        clear();
    }

    public Sprite(Vector2i size) {
        super(size); //me
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
        if (start.getQuadrant() == 1 && spritesheet.size.contains(new Vector2i(size).add(dim))) {
            for (int y = 0; y < dim.getY(); y++) {
                for (int x = 0; x < dim.getX(); x++) {
                    int col = spritesheet.pixels[start.getX() + x + (start.getY() + y) * spritesheet.size.getX()];
                    pixels[x + y * dim.getX()] = col;
                }
            }
        }
    }

    public Sprite flip(int axis) {
        Vector2i newSize = new Vector2i(size);
        Sprite out = new Sprite(newSize);
        for (int y = 0; y < size.getY(); y++) {
            for (int x = 0; x < size.getX(); x++) {
                Vector2i thisPixel = new Vector2i(x, y);
                Vector2i newPixel = new Vector2i();
                switch (axis%2) {
                    case 0:
                        newPixel = new Vector2i(newSize.getX() - thisPixel.getX(), thisPixel.getY());
                        break;
                    case 1:
                        newPixel = new Vector2i(newSize.getX(), newSize.getY() - thisPixel.getY());
                }
                out.setPixel(newPixel, getPixel(thisPixel));
            }
        }
        return out;
    }

    public Sprite rotate(int turns) {
        Vector2i newSize = new Vector2i(size).flip();
        Sprite out = new Sprite(newSize);
        for (int y = 0; y < size.getY(); y++) {
            for (int x = 0; x < size.getX(); x++) {
                Vector2i thisPixel = new Vector2i(x, y);
                Vector2i newPixel = new Vector2i();
                switch (turns%4) {
                    case 0:
                        newPixel = new Vector2i(thisPixel);
                        break;
                    case 1:
                        newPixel = new Vector2i(newSize.getX() - thisPixel.getY(), thisPixel.getX());
                        break;
                    case 2:
                        newPixel = new Vector2i(newSize.getX() - thisPixel.getX(), newSize.getY() - thisPixel.getY());
                        break;
                    case 3:
                        newPixel = new Vector2i(thisPixel.getY(), newSize.getY() - thisPixel.getX());
                        break;
                }
                out.setPixel(newPixel, getPixel(thisPixel));
            }
        }
        return out;
    }
}
