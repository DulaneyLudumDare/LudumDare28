package com.gregswebserver.ld28.game.level.tile;

import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.util.vectors.Vector2d;

public class TeleporterTile extends PathTile {

    public TeleporterTile(Vector2d position) {
        super(position);
    }

    public Sprite getSprite(){
        return Sprite.teleporter;
    }
}
