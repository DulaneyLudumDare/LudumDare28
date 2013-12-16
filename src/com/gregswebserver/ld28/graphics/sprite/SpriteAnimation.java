package com.gregswebserver.ld28.graphics.sprite;

import java.util.ArrayList;

public class SpriteAnimation {

    private ArrayList<Sprite> sprites = new ArrayList<>();
    private int i = 0;

    public Sprite next() {
        i++;
        if (i >= sprites.size()) i = 0;
        return sprites.get(i);
    }

    public SpriteAnimation addSprite(Sprite sprite) {
        sprites.add(sprite);
        return this;
    }
}
