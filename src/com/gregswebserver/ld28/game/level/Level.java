package com.gregswebserver.ld28.game.level;

import com.gregswebserver.ld28.game.level.tile.PathTile;
import com.gregswebserver.ld28.game.level.tile.Tile;
import com.gregswebserver.ld28.game.level.tile.WallTile;
import com.gregswebserver.ld28.graphics.screen.ScreenArea;
import com.gregswebserver.ld28.graphics.sprite.SpriteSheet;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2i;

import java.util.ArrayList;

public class Level {
    private ArrayList<Tile> tiles;
    private Vector2i size;

    public Level(String level) {
        load("/levels/" + level + ".png");
    }

    private void load(String path) {
        SpriteSheet lvl = new SpriteSheet(path, 128);

        for(int y = 0; y < lvl.size.getX(); y++) {
            for(int x = 0; x < lvl.size.getY(); x++) {
                Tile tile;
                if(lvl.pixels[x + y * lvl.size.getX()] == 0xff008000) {
                    tile = new PathTile(new Vector2i(x, y));
                    tiles.add(tile);
                } else if(lvl.pixels[x + y * lvl.size.getX()] == 0xff4f4f4f) {
                    tile = new WallTile(new Vector2i(x, y));
                    tiles.add(tile);
                } else if(lvl.pixels[x + y * lvl.size.getX()] == 0xff4f4f4f) {
                    tile = new WallTile(new Vector2i(x, y));
                    tiles.add(tile);
                } else if(lvl.pixels[x + y * lvl.size.getX()] == 0xff4f4f4f) {
                    tile = new WallTile(new Vector2i(x, y));
                    tiles.add(tile);
                } else {
                    if(x == 0) {
                        size.setY(y);
                        break;
                    }
                    size.setX(x);
                }
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
