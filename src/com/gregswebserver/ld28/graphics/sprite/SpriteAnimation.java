package com.gregswebserver.ld28.graphics.sprite;

import java.util.ArrayList;
import java.util.Iterator;

public class SpriteAnimation implements Iterable<Sprite> {

    public static SpriteAnimation stop_side = new SpriteAnimation().addSprite(Sprite.player_stop_side_0).addSprite(Sprite.player_stop_side_1).addSprite(Sprite.player_stop_side_2).addSprite(Sprite.player_stop_side_3);
    public static SpriteAnimation stop_up = new SpriteAnimation().addSprite(Sprite.player_stop_up_0).addSprite(Sprite.player_stop_up_1).addSprite(Sprite.player_stop_up_2).addSprite(Sprite.player_stop_up_3);
    public static SpriteAnimation stop_down = new SpriteAnimation().addSprite(Sprite.player_stop_down_0).addSprite(Sprite.player_stop_down_1).addSprite(Sprite.player_stop_down_2).addSprite(Sprite.player_stop_down_3);
    public static SpriteAnimation move_side = new SpriteAnimation().addSprite(Sprite.player_move_side_0).addSprite(Sprite.player_move_side_1).addSprite(Sprite.player_move_side_2).addSprite(Sprite.player_move_side_3);
    public static SpriteAnimation move_up = new SpriteAnimation().addSprite(Sprite.player_move_up_0).addSprite(Sprite.player_move_up_1).addSprite(Sprite.player_move_up_2).addSprite(Sprite.player_move_up_3);
    public static SpriteAnimation move_down = new SpriteAnimation().addSprite(Sprite.player_move_down_0).addSprite(Sprite.player_move_down_1).addSprite(Sprite.player_move_down_2).addSprite(Sprite.player_move_down_3);
    private ArrayList<Sprite> sprites = new ArrayList<>();

    public Iterator<Sprite> iterator() {
        return sprites.iterator();
    }

    public SpriteAnimation addSprite(Sprite sprite) {
        sprites.add(sprite);
        return this;
    }
}
