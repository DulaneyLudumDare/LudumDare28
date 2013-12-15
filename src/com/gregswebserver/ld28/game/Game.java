package com.gregswebserver.ld28.game;

import com.gregswebserver.ld28.graphics.screen.Screen;
import com.gregswebserver.ld28.graphics.screen.Window;
import com.gregswebserver.ld28.util.Debug;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class Game {

    public final Vector2i size = new Vector2i(512, 288).multiply(2);

    public Debug debug;
    public Screen screen;
    public Window window;

    private World world;

    public Game() {
        debug = new Debug();
        window = new Window(size, 1);
        screen = new Screen(size);
        loadNewGame("tutorial");
    }

    public void loadNewGame(String name) {
        world = new World(name, 3);
    }

    public void update() {
        world.getActivePlayer().setMoving(window.keyboard.getArrows());
        world.getActivePlayer().getLocation().tick();
        screen.clearObjects();
        world.getLevel().render(screen);
        world.getActivePlayer().render(screen);
    }

    public void render() {
        window.render(screen);
    }
}
