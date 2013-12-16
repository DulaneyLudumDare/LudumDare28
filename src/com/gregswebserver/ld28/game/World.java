package com.gregswebserver.ld28.game;

import com.gregswebserver.ld28.game.level.Level;
import com.gregswebserver.ld28.game.level.tile.Tile;
import com.gregswebserver.ld28.graphics.screen.Screen;
import com.gregswebserver.ld28.graphics.screen.ScreenObject;
import com.gregswebserver.ld28.util.vectors.Vector2d;
import com.gregswebserver.ld28.util.vectors.Vector2i;

import java.util.HashMap;

public class World {

    public Level level;
    public HashMap<Integer, Player> players;
    public int activePlayer = 0;

    public World(String name, int numPlayers) {
        players = new HashMap<>();
        level = new Level(name);
        for (int i = 0; i < numPlayers; i++) {
            players.put(i, new Player(level.getSpawnLocation()));
        }
    }

    public Player getActivePlayer() {
        return players.get(activePlayer);
    }

    public void render(Screen screen) {
        Vector2i offset = getActivePlayer().getLocation().getPosition().toVector2i();
        for (Tile tile : level.tiles.values()) {
            Vector2i location = tile.getPosition();
            screen.addObject("tile" + location.toString(), new ScreenObject(new Vector2i(location).multiply(32).subtract(offset), tile.getSprite(), 0));
        }
        for (Integer i : players.keySet()) {
            Player player = players.get(i);
            Vector2i playerCoord = new Vector2d(player.getLocation().getPosition()).multiply(32).toVector2i();
            System.out.println("player" + i + offset.toString());
            screen.addObject("player" + i, new ScreenObject(new Vector2d(player.getLocation().getPosition()).multiply(32).toVector2i().subtract(offset), player.getSprite(), 3));
        }

    }

}
