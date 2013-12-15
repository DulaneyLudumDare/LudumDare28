package com.gregswebserver.ld28.game;

import com.gregswebserver.ld28.graphics.screen.Scene;
import com.gregswebserver.ld28.graphics.screen.Screen;
import com.gregswebserver.ld28.graphics.screen.ScreenArea;
import com.gregswebserver.ld28.graphics.screen.ScreenObject;
import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.input.KeyboardHandler;
import com.gregswebserver.ld28.util.Debug;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class Game {

    public Debug debug;
    public Screen screen;

    private KeyboardHandler keyboard;
    private World world;

    private ScreenArea title;
    private ScreenArea vignette;

    private Scene menuScene;
    private Scene loadScene;
    private Scene hudScene;
    private Scene settingsScene;
    private Scene introScene;
    private Scene cutScene;
    private Scene titleScene;

    public Game() {
        debug = new Debug();
        screen = new Screen();
        keyboard = new KeyboardHandler();

        title = new ScreenArea(new Vector2i(128, 32), new Location(), 0);
        title.addObject("titleText", new ScreenObject(new Location(), Sprite.nullSprite, 0));
        vignette = new ScreenArea(screen.size, new Location(), 2);
        vignette.addObject("vignetteSprite", new ScreenObject(new Location(), Sprite.nullSprite, 0));

        //TODO fix all the null sprites in here

        menuScene = new Scene();
        {
            menuScene.addArea("title", title);

            ScreenArea optionList = new ScreenArea(new Vector2i(128, 640), new Location(), 0);
            optionList.addObject("exit", new ScreenObject(new Location(), Sprite.nullSprite, 0));
            menuScene.addArea("options", optionList);
        }

        loadScene = new Scene();
        {
            ScreenArea message = new ScreenArea(new Vector2i(128, 32), new Location(), 0);
            message.addObject("loading", new ScreenObject(new Location(), Sprite.nullSprite, 0));
            loadScene.addArea("loadMessage", message);
        }

        hudScene = new Scene();
        {
            hudScene.addArea("vignette", vignette);

            ScreenArea timer = new ScreenArea(new Vector2i(128, 32), new Location(), 3);
            timer.addObject("number", new ScreenObject(new Location(), Sprite.nullSprite, 1));
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
            bulletList.addObject("first", new ScreenObject(new Location(), Sprite.nullSprite, 0));
            introScene.addArea("bullets", bulletList);
        }

        cutScene = new Scene();
        {
//            cutScene.addArea("world", world.getLevel().getScreenArea());
//            cutScene.addArea("player", world.getActivePlayer().getScreenArea());
        }

        titleScene = new Scene();
        {
            titleScene.addArea("title", title);
        }

        loadNewGame("tutorial");
    }

    public void loadNewGame(String name) {
        world = new World(name, 3);
        screen.loadScene(hudScene);
        update();
    }

    public void update() {
        world.getActivePlayer().setMoving(keyboard.getArrowDir());
        screen.addArea("world", world.getLevel().getScreenArea());
        screen.addArea("player", world.getActivePlayer().getScreenArea());
        screen.update();
    }

    public void render() {
        screen.render();
    }
}
