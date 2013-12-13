package com.gregswebserver.ld28.graphics.sprite;

import com.gregswebserver.ld28.util.vectors.Vector2i;

public class SpriteText extends Sprite {

    public SpriteText(String text, int textSize) {
        size = new Vector2i(text.length() * textSize, textSize);
        for (int i = 0; i < text.length(); i++) {
            char part = text.charAt(i);
            Sprite charSprite = getSprite(part, textSize);
            for (int x = 0; x < charSprite.size.getX(); x++) {
                int xx = x + i * textSize;
                for (int y = 0; y < charSprite.size.getY(); y++) {
                    pixels[xx + (y * text.length() * textSize)] = charSprite.pixels[x + (y * charSprite.size.getX())];
                }
            }
        }


    }

    private Sprite getSprite(char part, int textSize) {
        return null;
    }
}
