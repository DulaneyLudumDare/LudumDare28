package com.gregswebserver.ld28.game.level;

import com.gregswebserver.ld28.game.UsesGame;
import com.gregswebserver.ld28.game.level.tile.*;
import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.graphics.sprite.SpriteSheet;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2d;
import com.gregswebserver.ld28.util.vectors.Vector2i;

import java.util.HashMap;
import java.util.Random;

public class Level extends UsesGame {

    public static int pathColor = 0xff008000;
    public static int wallColor = 0xff4f4f4f;
    public static int pathLandmarkColor = 0xffffff00;
    public static int wallLandmarkColor = 0xff00ffff;

    public HashMap<Vector2d, Tile> tiles = new HashMap<>();
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
                Vector2d location = new Vector2d(x, y);
                int color = lvl.getPixel(location.toVector2i());
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
            HashMap<Vector2d, Tile> children = getNeighbors(parent);
            int type = 0;
            int rotation = 0;
            Vector2d cumulative = new Vector2d();
            for (Vector2d child : children.keySet()) {
                cumulative.add(child.unit());
            }
            switch (children.size()) {
                case 3:
                    type = 2;
                    rotation = cumulative.getQuadrant() - 1;
                    break;
                case 5:
                case 6:
                    type = 1;
                    rotation = 4 - cumulative.getDirection();
                    break;
                case 7:
                    type = 3;
                    rotation = cumulative.getQuadrant() - 1;
                    break;
                default:
                    type = 0;
                    rotation = 0;
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

    private HashMap<Vector2d, Tile> getNeighbors(Tile center) {
        HashMap<Vector2d, Tile> adjacentTiles = new HashMap<>();
        for (int i = 0; i <= 8; i++) {
            if (i == 4) continue;
            Vector2d childPosition = new Vector2d((i / 3) - 1, (i % 3) - 1);
            Tile adjacent = tiles.get(center.getPosition().copy().add(childPosition));
            if ((center instanceof WallTile && adjacent instanceof WallTile) || (center instanceof PathTile && adjacent instanceof PathTile))
                adjacentTiles.put(childPosition, adjacent);
        }
        return adjacentTiles;
    }

    public HashMap<Vector2d, Tile> getAdjacentTiles(Vector2d location) {
        HashMap<Vector2d, Tile> adjacentTiles = new HashMap<>();
        for (int i = 0; i <= 8; i++) {
            if (i == 4) continue;
            Vector2d childPosition = new Vector2d((i / 3) - 1, (i % 3) - 1);
            Tile adjacent = tiles.get(location.copy().add(childPosition).toVector2i().toVector2d());
            if (adjacent != null)
                adjacentTiles.put(childPosition, adjacent);
        }
        return adjacentTiles;
    }

    public Location getSpawnLocation() {
        Random random = new Random();
        do {
            Vector2d position = new Vector2d(random.nextInt(size.getX()), random.nextInt(size.getY()));
            if (tiles.get(position) instanceof PathTile)
                return new Location(position);
        } while (true);
    }
}
