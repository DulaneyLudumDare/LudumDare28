package com.gregswebserver.ld28.game;

import com.gregswebserver.ld28.graphics.screen.*;
import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.input.KeyboardHandler;
import com.gregswebserver.ld28.util.Debug;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class Game {

    public final Vector2i size = new Vector2i(640, 480);

    public Debug debug;
    public Screen screen;
    public Window window;

    private KeyboardHandler keyboard;
    private World world;

    private ScreenArea title;
    private ScreenArea vignette;

    private Scene titleScene;
    private Scene menuScene;
    private Scene introScene;
    private Scene loadScene;
    private Scene settingsScene;

    public Game() {
        debug = new Debug();
        window = new Window(size);
        screen = new Screen(size);
        keyboard = new KeyboardHandler();

        title = new ScreenArea(new Vector2i(128, 32), new Location(), 0);
        title.addObject("titleText", new ScreenObject(new Location(), Sprite.nullSprite, 0));
        vignette = new ScreenArea(screen.size, new Location(), 2);
        vignette.addObject("vignetteSprite", new ScreenObject(new Location(), Sprite.nullSprite, 0));

        //TODO fix all the null sprites in here

        titleScene = new Scene();
        {
            titleScene.addArea("title", title);
        }

        menuScene = new Scene();
        {
            menuScene.addArea("title", title);

            ScreenArea optionList = new ScreenArea(new Vector2i(128, 640), new Location(), 0);
            optionList.addObject("exit", new ScreenObject(new Location(), Sprite.nullSprite, 0));
            menuScene.addArea("options", optionList);
        }

        introScene = new Scene();
        {
            introScene.addArea("title", title);
            ScreenArea bulletList = new ScreenArea(new Vector2i(128, 640), new Location(), 0);
            bulletList.addObject("first", new ScreenObject(new Location(), Sprite.nullSprite, 0));
            introScene.addArea("bullets", bulletList);
        }

        loadScene = new Scene();
        {
            ScreenArea message = new ScreenArea(new Vector2i(128, 32), new Location(), 0);
            message.addObject("loading", new ScreenObject(new Location(), Sprite.nullSprite, 0));
            loadScene.addArea("loadMessage", message);
        }

        settingsScene = new Scene();
        {
            ScreenArea title = new ScreenArea(new Vector2i(256, 32), new Location(), 0);
            settingsScene.addArea("title", title);
        }

        loadNewGame("tutorial");
    }

    public void loadNewGame(String name) {
        world = new World(name, 3);
        update();
    }

    public void update() {
        long time = System.currentTimeMillis();
        world.getActivePlayer().setMoving(keyboard.getArrowDir());
        screen.addArea("player", world.getActivePlayer().getScreenArea());
        screen.addArea("level", world.getLevelRender());
        screen.update();
        debug.printDebug(5, "Updating " + (System.currentTimeMillis() - time) + " ms");
    }

    public void render() {
        long time = System.currentTimeMillis();
        window.render(screen);
        debug.printDebug(5, "Rendering " + (System.currentTimeMillis() - time) + " ms");
    }
}
