package com.gregswebserver.ld28.game.level.tile;

import com.gregswebserver.ld28.util.vectors.Vector2d;

public class WallTile extends Tile {
    public WallTile(Vector2d position) {
        super(position);
    }

    public boolean isSolid() {
        return true;
    }
}
