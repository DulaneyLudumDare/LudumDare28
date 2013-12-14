package com.gregswebserver.ld28.game;

import com.gregswebserver.ld28.graphics.screen.Scene;
import com.gregswebserver.ld28.graphics.screen.Screen;
import com.gregswebserver.ld28.graphics.screen.ScreenArea;
import com.gregswebserver.ld28.graphics.screen.ScreenObject;
import com.gregswebserver.ld28.util.Debug;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class Game {

    public Debug debug;
    public Screen screen;

    private ScreenArea title;
    private ScreenArea world;
    private ScreenArea player;
    private ScreenArea vignette;

    private Scene menuScene;
    private Scene hudScene;
    private Scene settingsScene;
    private Scene introScene;
    private Scene cutScene;
    private Scene titleScene;

    public Game() {
        screen = new Screen();

        title = new ScreenArea(new Vector2i(128, 32), new Location(), 0);
        title.addObject("titleText", new ScreenObject(new Location(), null, 0));
        world = new ScreenArea(screen.size, new Location(), 0);
        player = new ScreenArea(new Vector2i(32, 64), new Location(), 1);
        vignette = new ScreenArea(screen.size, new Location(), 2);
        vignette.addObject("vignetteSprite", new ScreenObject(new Location(), null, 0));

        menuScene = new Scene();
        {
            menuScene.addArea("title", title);

            ScreenArea optionList = new ScreenArea(new Vector2i(128, 640), new Location(), 0);
            optionList.addObject("exit", new ScreenObject(new Location(), null, 0));
            menuScene.addArea("options", optionList);
        }

        hudScene = new Scene();
        {
            hudScene.addArea("world", world);
            hudScene.addArea("player", player);
            hudScene.addArea("vignette", vignette);

            ScreenArea timer = new ScreenArea(new Vector2i(128, 32), new Location(), 3);
            timer.addObject("number", new ScreenObject(new Location(), null, 1));
            hudScene.addArea("timer", timer);
        }

        settingsScene = new Scene();
        {
            ScreenArea title = new ScreenArea(new Vector2i(256, 32), new Location(), 0);
            settingsScene.addArea("title", title);
        }

        introScene = new Scene();
        {
            introScene.addArea("title", title);
            ScreenArea bulletList = new ScreenArea(new Vector2i(128, 640), new Location(), 0);
            bulletList.addObject("first", new ScreenObject(new Location(), null, 0));
            introScene.addArea("bullets", bulletList);
        }

        cutScene = new Scene();
        {
            cutScene.addArea("world", world);
            cutScene.addArea("player", player);
        }

        titleScene = new Scene();
        {
            titleScene.addArea("title", title);
        }
    }

    public void loadWorld() {
        world.clear();
        world.addObject("tile", new ScreenObject(new Location(), null, 0));
    }

    public void loadPlayer() {
        player.clear();
        player.addObject("playerSprite", new ScreenObject(new Location(), null, 0));
    }

    public void update() {
        screen.update();
    }

    public void render() {
        screen.update();
    }
}
