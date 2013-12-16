package com.gregswebserver.ld28.game.level;

import com.gregswebserver.ld28.game.UsesGame;
import com.gregswebserver.ld28.util.vectors.Vector2d;

public abstract class WorldObject extends UsesGame {

    protected Vector2d position;

    public abstract Boundary getBoundary();

    public abstract boolean isSolid();

    public Vector2d getPosition() {
        return position;
    }

}
