package com.gregswebserver.ld28.game.level;

import com.gregswebserver.ld28.game.level.tile.Tile;
import com.gregswebserver.ld28.graphics.screen.ScreenArea;
import com.gregswebserver.ld28.graphics.sprite.SpriteSheet;
import com.gregswebserver.ld28.util.Location;

import java.util.ArrayList;

public class Level {
    private ArrayList<Tile> tiles;

    public Level(String level) {
        load("/graphics/" + level);
    }

    private void load(String path) {
        SpriteSheet lvl = new SpriteSheet(path, 128);

        for(int x = 0; x < lvl.size.getX(); x++) {
            for(int y = 0; y < lvl.size.getY(); y++) {
                //writing
            }
        }
    }

    public Location getSpawnLocation() {
        return new Location();
    }

    public ScreenArea getScreenArea() {
        return null;
    }
}
