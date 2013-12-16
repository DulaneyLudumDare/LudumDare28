package com.gregswebserver.ld28.game;

import com.gregswebserver.ld28.game.level.World;
import com.gregswebserver.ld28.graphics.screen.Screen;
import com.gregswebserver.ld28.graphics.screen.Window;
import com.gregswebserver.ld28.util.Debug;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class Game {

    public final Vector2i size = new Vector2i(512, 288).multiply(1);

    public Debug debug;
    public Screen screen;
    public Window window;

    private World world;

    public Game() {
        debug = new Debug();
        window = new Window(size, 2);
        screen = new Screen(size);
        loadNewGame("tutorial");
    }

    public void loadNewGame(String name) {
        world = new World(name, 3);
    }

    public void update() {
        world.input(window.keyboard.getArrows());
        world.tick();
        screen.clearObjects();
        world.render(screen);
    }

    public void render() {
        window.render(screen);
    }
}
