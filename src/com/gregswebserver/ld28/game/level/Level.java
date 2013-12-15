package com.gregswebserver.ld28.game.level;

import com.gregswebserver.ld28.game.UsesGame;
import com.gregswebserver.ld28.game.level.tile.*;
import com.gregswebserver.ld28.graphics.screen.Screen;
import com.gregswebserver.ld28.graphics.screen.ScreenObject;
import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.graphics.sprite.SpriteSheet;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2i;

import java.util.HashMap;
import java.util.Random;

public class Level extends UsesGame {

    public static int pathColor = 0xff008000;
    public static int wallColor = 0xff4f4f4f;
    public static int pathLandmarkColor = 0xffffff00;
    public static int wallLandmarkColor = 0xff00ffff;

    private HashMap<Vector2i, Tile> tiles = new HashMap<>();
    private Vector2i size;

    public Level(String level) {
        load("/levels/" + level + ".png");
        finalizeOrientations();
    }

    private void load(String path) {
        SpriteSheet lvl = new SpriteSheet(path, 128);
        size = new Vector2i();

        for (int y = 0; y < lvl.size.getX(); y++) {
            for (int x = 0; x < lvl.size.getY(); x++) {
                Vector2i location = new Vector2i(x, y);
                int color = lvl.getPixel(location);
                if (color == pathColor)
                    tiles.put(location, new PathTile(location));
                else if (color == wallColor)
                    tiles.put(location, new WallTile(location));
                else if (color == pathLandmarkColor)
                    tiles.put(location, new PathLandmarkTile(location));
                else if (color == wallLandmarkColor)
                    tiles.put(location, new WallLandmarkTile(location));
                else {
                    if (x == 0) {
                        size.setY(y);
                        return;
                    }
                    size.setX(x);
                    break;
                }
            }
        }
    }

    private void finalizeOrientations() {
        for (Tile parent : tiles.values()) {
            HashMap<Vector2i, Tile> children = getNeighbors(parent);
            int type = 0;
            int rotation = 0;
            Vector2i cumulative = new Vector2i();
            for (Vector2i child : children.keySet()) {
                cumulative.add(child);
            }
            switch (children.size()) {
                case 3:
                    type = 2;
                    rotation = cumulative.getQuadrant();
                    break;
                case 5:
                case 6:
                    type = 1;
                    rotation = cumulative.getDirection();
                    break;
                case 7:
                    type = 3;
                    rotation = cumulative.getQuadrant();
            }
            setSprite(parent, type, rotation);
        }
    }

    private void setSprite(Tile tile, int type, int rotation) {
        if (tile instanceof WallTile) {
            switch (type) {
                case 0:
                    tile.setSprite(Sprite.wall_flat);
                    break;
                case 1:
                    tile.setSprite(Sprite.wall_straight.rotate(rotation));
                    break;
                case 2:
                    tile.setSprite(Sprite.wall_corner_out.rotate(rotation));
                    break;
                case 3:
                    tile.setSprite(Sprite.wall_corner_in.rotate(rotation));
            }
        } else if (tile instanceof PathTile) {
            switch (type) {
                case 0:
                    tile.setSprite(Sprite.path_flat);
                    break;
                case 1:
                    tile.setSprite(Sprite.path_straight.rotate(rotation));
                    break;
                case 2:
                    tile.setSprite(Sprite.path_corner_out.rotate(rotation));
                    break;
                case 3:
                    tile.setSprite(Sprite.path_corner_in.rotate(rotation));
            }
        }
    }

    private HashMap<Vector2i, Tile> getNeighbors(Tile parent) {

        HashMap<Vector2i, Tile> neighbors = new HashMap<>();
        for (int i = -1; i <= 8; i++) {
            if (i == 4) continue;
            Vector2i childPosition = new Vector2i((i / 3) - 1, (i % 3) - 1);
            Tile child = tiles.get(new Vector2i(parent.getPosition()).add(childPosition));
            if ((child instanceof WallTile && parent instanceof WallTile) || (child instanceof PathTile && parent instanceof PathTile)) {
                neighbors.put(childPosition, child);
            }
        }
        return neighbors;
    }

    public Location getSpawnLocation() {
        Random random = new Random();
        do {
            Vector2i position = new Vector2i(random.nextInt(size.getX()), random.nextInt(size.getY()));
            if (tiles.get(position) instanceof PathTile)
                return new Location(position.toVector2d());
        } while (true);
    }

    public void render(Screen screen) {
        for (Tile tile : tiles.values()) {
            Vector2i location = tile.getPosition();
            screen.addObject("tile" + location.toString(), new ScreenObject(new Vector2i(location).scale(32), tile.getSprite(), 0));
        }
    }
}
