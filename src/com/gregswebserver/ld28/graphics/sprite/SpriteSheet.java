package com.gregswebserver.ld28.graphics.sprite;

import com.gregswebserver.ld28.graphics.util.Graphic;
import com.gregswebserver.ld28.util.vectors.Vector2i;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet extends Graphic {

    public static SpriteSheet terrain = new SpriteSheet("/graphics/terrain.png", 256);
    public static SpriteSheet landmarks = new SpriteSheet("/graphics/landmarks.png", 128);
    public static SpriteSheet player = new SpriteSheet("/graphics/player.png", 256);
    public static SpriteSheet vignette = new SpriteSheet("/graphics/vignette.png", new Vector2i(512, 288));

    public SpriteSheet(String path, int size) {
        super(new Vector2i(size));
        load(path);
    }

    public SpriteSheet(String path, Vector2i dim) {
        super(dim);
        load(path);
    }

    private void load(String path) {
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        } catch (IOException e) {
            game.debug.printStack(2, e);
        }
    }
}
