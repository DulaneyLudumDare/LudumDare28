package com.gregswebserver.ld28.game.level;

import com.gregswebserver.ld28.game.level.tile.*;
import com.gregswebserver.ld28.graphics.screen.ScreenArea;
import com.gregswebserver.ld28.graphics.sprite.SpriteSheet;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2i;

import java.util.ArrayList;

public class Level {
    private ArrayList<Tile> tiles = new ArrayList<>();
    private Vector2i size;

    public Level(String level) {
        load("/levels/" + level + ".png");
        finalizeOrentations();
    }

    private void load(String path) {
        SpriteSheet lvl = new SpriteSheet(path, 128);

        for (int y = 0; y < lvl.size.getX(); y++) {
            for (int x = 0; x < lvl.size.getY(); x++) {
                if (lvl.pixels[x + y * lvl.size.getX()] == 0xff008000)
                    tiles.add(new PathTile(new Vector2i(x, y)));
                else if (lvl.pixels[x + y * lvl.size.getX()] == 0xff4f4f4f)
                    tiles.add(new WallTile(new Vector2i(x, y)));
                else if (lvl.pixels[x + y * lvl.size.getX()] == 0xffffff00)
                    tiles.add(new PathLandmarkTile(new Vector2i(x, y)));
                else if (lvl.pixels[x + y * lvl.size.getX()] == 0xff00ffff)
                    tiles.add(new WallLandmarkTile(new Vector2i(x, y)));
                else {
                    if (x == 0) {
                        size.setY(y);
                        break;
                    }
                    size.setX(x);
                }
            }
        }
    }

    private void finalizeOrentations() {
        for (int y = 0; y < size.getY(); y++) {
            for (int x = 0; x < size.getX(); x++) {
                if(getNeighors(x, y).get(0) == null) {

                } else if(getNeighors(x, y).get(0) instanceof PathTile) {

                } else if(getNeighors(x, y).get(0) instanceof WallTile) {

                } else if(getNeighors(x, y).get(0) instanceof PathLandmarkTile) {

                } else if(getNeighors(x, y).get(0) instanceof WallTile) {

                }
            }
        }
    }

    private ArrayList<Tile> getNeighors(int x, int y) {
        ArrayList<Tile> neighbors = new ArrayList<>();
        if(y > 0)
            neighbors.add(tiles.get(x + (y - 1) * size.getX()));
        else
            neighbors.add(null);

        if(x < size.getX() - 1)
            neighbors.add(tiles.get((x + 1) + y * size.getX()));
        else
            neighbors.add(null);

        if(y < size.getY() - 1)
            neighbors.add(tiles.get(x + (y + 1) * size.getX()));
        else
            neighbors.add(null);

        if(x > 0)
            neighbors.add(tiles.get((x - 1) + y * size.getX()));
        else
            neighbors.add(null);

        return neighbors;
    }

    public Location getSpawnLocation() {
        return new Location();
    }

    public ScreenArea getScreenArea() {
        return null;
    }
}
