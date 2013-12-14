package com.gregswebserver.ld28.game;

import com.gregswebserver.ld28.game.level.Level;

import java.util.HashMap;

public class World {

    public Level level;
    public HashMap<Integer, Player> players;
    public int activePlayer = 0;

    public World(String name, int numPlayers) {
        players = new HashMap<Integer, Player>();
        //Create new level
        for (int i = 0; i < numPlayers; i++) {
            players.put(i, new Player(null));
        }
    }

    public Player getActivePlayer() {
        return players.get(activePlayer);
    }

    public Level getLevel() {
        return level;
    }

}
