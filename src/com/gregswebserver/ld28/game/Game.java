package com.gregswebserver.ld28.game;

import com.gregswebserver.ld28.graphics.Screen;
import com.gregswebserver.ld28.util.Debug;

public class Game {

    public Debug debug;
    public Screen screen;

    public Game() {
        screen = new Screen();
    }

    public void update() {
        screen.update();
    }

    public void render() {
        screen.update();
    }
}
