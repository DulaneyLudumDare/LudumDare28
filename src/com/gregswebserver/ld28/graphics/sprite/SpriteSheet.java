package com.gregswebserver.ld28.graphics.sprite;

import com.gregswebserver.ld28.graphics.Graphic;
import com.gregswebserver.ld28.util.vectors.Vector2i;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet extends Graphic {

    public SpriteSheet(String path, int size) {
        super(new Vector2i(size));
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
