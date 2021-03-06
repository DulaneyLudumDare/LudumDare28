package com.gregswebserver.ld28.game.level;

import com.gregswebserver.ld28.game.UsesGame;
import com.gregswebserver.ld28.game.level.tile.PathLandmarkTile;
import com.gregswebserver.ld28.game.level.tile.Tile;
import com.gregswebserver.ld28.graphics.screen.Screen;
import com.gregswebserver.ld28.graphics.screen.ScreenObject;
import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.util.vectors.Vector2d;
import com.gregswebserver.ld28.util.vectors.Vector2i;

import java.util.HashMap;

public class World extends UsesGame {

    public Level level;
    public HashMap<Integer, Player> players;
    public int activePlayer = 0;
    public int animStep = 0;

    public void loadNew(String name, int numPlayers) {
        players = new HashMap<>();
        level = new Level(name);
        for (int i = 0; i < numPlayers; i++) {
            players.put(i, new Player(level.getSpawnLocation()));
        }
        activePlayer = 0;
        animStep = 0;
    }

    public Player getActivePlayer() {
        return players.get(activePlayer);
    }

    public void render(Screen screen) {
        switch (game.scene) {
            case 0:
                screen.addObject("title", new ScreenObject(screen.size.copy().divide(2).subtract(Sprite.title.size.copy().divide(2)), Sprite.title, 0));
                break;
            case 1:
                screen.addObject("intro", new ScreenObject(new Vector2i(screen.size.copy().divide(2).subtract(Sprite.intro.size.copy().divide(2)).getX(), 288 - (animStep / 6)), Sprite.intro, 0));
                break;
            default:
                worldRender(screen);
        }
    }

    private void worldRender(Screen screen) {
        for (Tile tile : level.tiles.values()) {
            Vector2d location = tile.getPosition();
            if (screen.getBoundary().conflicts(new Boundary(getScreenLocation(screen, location).toVector2d(), new Vector2d(32)))) {
                screen.addObject("tile" + location.toString(), new ScreenObject(getScreenLocation(screen, location), tile.getSprite(), 0));
                if (tile instanceof PathLandmarkTile) {
                    screen.addObject("tile" + location.toString() + "o", new ScreenObject(getScreenLocation(screen, location), ((PathLandmarkTile) tile).getFeature(), 1));
                }
            }
        }
        for (Integer i : players.keySet()) {
            Player player = players.get(i);
            screen.addObject("player" + i, new ScreenObject(getScreenLocation(screen, player.getLocation().getPosition()), player.getSprite(), 3));
        }
        screen.addObject("vignette", new ScreenObject(new Vector2i(), Sprite.vignette, 4));
    }

    private Vector2i getScreenLocation(Screen screen, Vector2d in) {
        Vector2i pixelCentering = new Vector2i(screen.size).divide(2).subtract(new Vector2i(16, 16));
        Vector2i offset = getActivePlayer().getLocation().getPosition().copy().multiply(32).toVector2i().subtract(pixelCentering);
        return in.copy().multiply(32).toVector2i().subtract(offset);
    }

    public void tick() {
        Vector2d activeLocation = getActivePlayer().getLocation().getPosition();
        for (Integer i : players.keySet()) {
            Player player = players.get(i);
            if (i != activePlayer) {
                Vector2d bearing = player.getLocation().getPosition().copy().subtract(activeLocation);
                if (bearing.lengthSquared() < 10 && bearing.lengthSquared() > 1.5)
                    player.setMoving(new Vector2d(-bearing.getX(), bearing.getY()));
                else
                    player.setMoving(new Vector2d());
            }
            Boundary currentBoundary = player.getBoundary();
            Boundary playerBoundary = player.getNextBoundary();
            HashMap<Vector2d, Tile> tiles = level.getAdjacentTiles(player.getLocation().copy().getPosition());
            boolean conflict = false;
            for (Tile tile : tiles.values()) {
                if (tile.isSolid() && tile.getBoundary().conflicts(playerBoundary) && !tile.getBoundary().conflicts(currentBoundary)) {
                    conflict = true;
                }
            }
            if (conflict) player.getLocation().setVel(new Vector2d());
            player.getLocation().tick();
        }
    }

    public int playersSurroundingExit() {
        int count = 0;
        for (Player player : players.values()) {
            Vector2d dist = player.getLocation().getPosition().copy().subtract(level.endLocation);
            if (dist.lengthSquared() < 20)
                count++;
        }
        return count;
    }

    public void input(Vector2d direction) {
        getActivePlayer().setMoving(direction);
    }

}
