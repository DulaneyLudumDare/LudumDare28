package com.gregswebserver.ld28.graphics.sprite;

import com.gregswebserver.ld28.graphics.util.Graphic;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class Sprite extends Graphic {

    public static Sprite nullSprite = new Sprite(new Vector2i(7, 7), 32, SpriteSheet.terrain);
    public static Sprite vignette = new Sprite(new Vector2i(), new Vector2i(512, 288), SpriteSheet.vignette);

    //terrain sprites
    public static Sprite path_flat = new Sprite(new Vector2i(0, 0), 32, SpriteSheet.terrain);
    public static Sprite path_straight = new Sprite(new Vector2i(1, 0), 32, SpriteSheet.terrain);
    public static Sprite path_corner_out = new Sprite(new Vector2i(2, 0), 32, SpriteSheet.terrain);
    public static Sprite path_corner_in = new Sprite(new Vector2i(3, 0), 32, SpriteSheet.terrain);
    public static Sprite wall_flat = new Sprite(new Vector2i(0, 1), 32, SpriteSheet.terrain);
    public static Sprite wall_straight = new Sprite(new Vector2i(1, 1), 32, SpriteSheet.terrain);
    public static Sprite wall_corner_out = new Sprite(new Vector2i(2, 1), 32, SpriteSheet.terrain);
    public static Sprite wall_corner_in = new Sprite(new Vector2i(3, 1), 32, SpriteSheet.terrain);

    //landmark sprites
    public static Sprite landmark_wall = new Sprite(new Vector2i(0, 0), 32, SpriteSheet.landmarks);
    public static Sprite landmark_path = new Sprite(new Vector2i(0, 0), 32, SpriteSheet.landmarks);

    //player sprites
    public static int playerSize = 32;

    public static Sprite player_stop_straight_0 = new Sprite(new Vector2i(0, 0), playerSize, SpriteSheet.player);
    public static Sprite player_stop_straight_1 = new Sprite(new Vector2i(1, 0), playerSize, SpriteSheet.player);
    public static Sprite player_stop_straight_2 = new Sprite(new Vector2i(2, 0), playerSize, SpriteSheet.player);
    public static Sprite player_stop_straight_3 = new Sprite(new Vector2i(3, 0), playerSize, SpriteSheet.player);
    public static Sprite player_stop_diagonal_0 = new Sprite(new Vector2i(0, 1), playerSize, SpriteSheet.player);
    public static Sprite player_stop_diagonal_1 = new Sprite(new Vector2i(1, 1), playerSize, SpriteSheet.player);
    public static Sprite player_stop_diagonal_2 = new Sprite(new Vector2i(2, 1), playerSize, SpriteSheet.player);
    public static Sprite player_stop_diagonal_3 = new Sprite(new Vector2i(3, 1), playerSize, SpriteSheet.player);

    public static Sprite player_move_straight_0 = new Sprite(new Vector2i(4, 0), playerSize, SpriteSheet.player);
    public static Sprite player_move_straight_1 = new Sprite(new Vector2i(5, 0), playerSize, SpriteSheet.player);
    public static Sprite player_move_straight_2 = new Sprite(new Vector2i(6, 0), playerSize, SpriteSheet.player);
    public static Sprite player_move_straight_3 = new Sprite(new Vector2i(7, 0), playerSize, SpriteSheet.player);
    public static Sprite player_move_diagonal_0 = new Sprite(new Vector2i(4, 1), playerSize, SpriteSheet.player);
    public static Sprite player_move_diagonal_1 = new Sprite(new Vector2i(5, 1), playerSize, SpriteSheet.player);
    public static Sprite player_move_diagonal_2 = new Sprite(new Vector2i(6, 1), playerSize, SpriteSheet.player);
    public static Sprite player_move_diagonal_3 = new Sprite(new Vector2i(7, 1), playerSize, SpriteSheet.player);

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
        for (int y = 0; y < dim.getY(); y++) {
            for (int x = 0; x < dim.getX(); x++) {
                int col = spritesheet.pixels[start.getX() + x + (start.getY() + y) * spritesheet.size.getX()];
                pixels[x + y * dim.getX()] = col;
            }
        }
    }

    public Sprite rotate(int turns) {
        Vector2i newSize = new Vector2i(size).flip();
        Sprite out = new Sprite(newSize);
        for (int y = 0; y < size.getY(); y++) {
            for (int x = 0; x < size.getX(); x++) {
                Vector2i thisPixel = new Vector2i(x, y);
                Vector2i newPixel = new Vector2i();
                switch (turns % 4) {
                    case 0:
                        newPixel = new Vector2i(thisPixel);
                        break;
                    case 1:
                        newPixel = new Vector2i(size.getY() - 1 - thisPixel.getY(), thisPixel.getX());
                        break;
                    case 2:
                        newPixel = new Vector2i(size.getX() - 1 - thisPixel.getX(), size.getY() - 1 - thisPixel.getY());
                        break;
                    case 3:
                        newPixel = new Vector2i(thisPixel.getY(), size.getX() - 1 - thisPixel.getX());
                        break;

                }
                out.setPixel(newPixel, getPixel(thisPixel));
            }
        }
        return out;
    }
}
