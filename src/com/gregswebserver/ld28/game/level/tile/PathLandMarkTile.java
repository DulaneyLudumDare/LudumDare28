package com.gregswebserver.ld28.game.level.tile;

import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class PathLandMarkTile extends Tile{
    public PathLandMarkTile(Vector2i coord) {
        super(coord, Sprite.landmark_path);
    }
}
