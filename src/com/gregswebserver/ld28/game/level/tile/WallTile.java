package com.gregswebserver.ld28.game.level.tile;

import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class WallTile extends Tile{
    public WallTile(Vector2i coord) {
        super(coord, Sprite.wall_flat);
    }
}
