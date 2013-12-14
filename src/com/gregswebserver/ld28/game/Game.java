package com.gregswebserver.ld28.game;

import com.gregswebserver.ld28.graphics.screen.Scene;
import com.gregswebserver.ld28.graphics.screen.Screen;
import com.gregswebserver.ld28.util.Debug;

public class Game {

    public Debug debug;
    public Screen screen;

    private Scene menuScene = new Scene();
    private Scene hudScene = new Scene();
    private Scene introScene = new Scene();
    private Scene cutScene = new Scene();
    private Scene titleScene = new Scene();

    public Game() {
        screen = new Screen();

        menuScene = new Scene();

        hudScene = new Scene();

        introScene = new Scene();

        cutScene = new Scene();

        titleScene = new Scene();

    }

    public void update() {
        screen.update();
    }

    public void render() {
        screen.update();
    }
}
