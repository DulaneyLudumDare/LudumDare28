package com.gregswebserver.ld28.graphics.sprite;

import java.util.ArrayList;

public class SpriteAnimation {

    public static SpriteAnimation stop_right = new SpriteAnimation().addSprite(Sprite.player_stop_right_0).addSprite(Sprite.player_stop_right_1).addSprite(Sprite.player_stop_right_2).addSprite(Sprite.player_stop_right_3);
    public static SpriteAnimation stop_left = new SpriteAnimation().addSprite(Sprite.player_stop_left_0).addSprite(Sprite.player_stop_left_1).addSprite(Sprite.player_stop_left_2).addSprite(Sprite.player_stop_left_3);
    public static SpriteAnimation stop_up = new SpriteAnimation().addSprite(Sprite.player_stop_up_0).addSprite(Sprite.player_stop_up_1).addSprite(Sprite.player_stop_up_2).addSprite(Sprite.player_stop_up_3);
    public static SpriteAnimation stop_down = new SpriteAnimation().addSprite(Sprite.player_stop_down_0).addSprite(Sprite.player_stop_down_1).addSprite(Sprite.player_stop_down_2).addSprite(Sprite.player_stop_down_3);
    public static SpriteAnimation move_right = new SpriteAnimation().addSprite(Sprite.player_move_right_0).addSprite(Sprite.player_move_right_1).addSprite(Sprite.player_move_right_2).addSprite(Sprite.player_move_right_3);
    public static SpriteAnimation move_left = new SpriteAnimation().addSprite(Sprite.player_move_left_0).addSprite(Sprite.player_move_left_1).addSprite(Sprite.player_move_left_2).addSprite(Sprite.player_move_left_3);
    public static SpriteAnimation move_up = new SpriteAnimation().addSprite(Sprite.player_move_up_0).addSprite(Sprite.player_move_up_1).addSprite(Sprite.player_move_up_2).addSprite(Sprite.player_move_up_3);
    public static SpriteAnimation move_down = new SpriteAnimation().addSprite(Sprite.player_move_down_0).addSprite(Sprite.player_move_down_1).addSprite(Sprite.player_move_down_2).addSprite(Sprite.player_move_down_3);

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
