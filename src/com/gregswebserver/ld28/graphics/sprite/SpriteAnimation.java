package com.gregswebserver.ld28.graphics.sprite;

import java.util.ArrayList;
import java.util.Iterator;

public class SpriteAnimation implements Iterable<Sprite>{

    public static SpriteAnimation side = new SpriteAnimation().addSprite(Sprite.player_side_0).addSprite(Sprite.player_side_1).addSprite(Sprite.player_side_2).addSprite(Sprite.player_side_3);
    public static SpriteAnimation up = new SpriteAnimation().addSprite(Sprite.player_up_0).addSprite(Sprite.player_up_1).addSprite(Sprite.player_up_2).addSprite(Sprite.player_up_3);
    public static SpriteAnimation down = new SpriteAnimation().addSprite(Sprite.player_down_0).addSprite(Sprite.player_down_1).addSprite(Sprite.player_down_2).addSprite(Sprite.player_down_3);

    private ArrayList<Sprite> sprites = new ArrayList<>();

    public Iterator<Sprite> iterator() {
        return sprites.iterator();
    }

    public Sprite getDefault(){
        return sprites.get(0);
    }

    public SpriteAnimation addSprite(Sprite sprite){
        sprites.add(sprite);
        return this;
    }
}
