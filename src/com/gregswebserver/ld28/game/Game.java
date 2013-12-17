package com.gregswebserver.ld28.game;

import com.gregswebserver.ld28.audio.Audio;
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

    public int scene = 0;

    private World world;

    public Game() {
        debug = new Debug();
        window = new Window(size, 2);
        screen = new Screen(size);
        world = new World();
        loadNewGame("tutorial");
    }

    public void loadNewGame(String name) {
//        Audio.dripping.setVolume(-10);
//        Audio.dripping.stop();
        world.loadNew(name, 3);
//        Audio.dripping.play();
    }

    public void update() {
        world.input(window.keyboard.getArrows());
        switch (scene) {
            case 0:
//                Audio.cave_sound_1.setVolume(-40);
//                Audio.cave_sound_1.loop();
                if (window.keyboard.hasKeyPressed()) scene = 1;
                world.animStep = 0;
                break;
            case 1:
//                Audio.cave_sound_2.setVolume(-40);
//                Audio.cave_sound_1.stop();
//                Audio.cave_sound_2.loop();
                world.animStep++;
                if ((world.animStep > 30) && window.keyboard.hasKeyPressed()) scene = 2;
                break;
            case 2:
//                Audio.cave_sound_2.stop();
                loadNewGame("tutorial");
                scene = 3;
                break;
            case 3:
//                Audio.beam.setVolume(-20);
                world.tick();
                if (world.players.size() == world.playersSurroundingExit()) {
//                    Audio.beam.play();
                    scene = 4;
                }
                break;
            case 4:
                loadNewGame("maze1");
                scene = 5;
                break;
            case 5:
                world.tick();
                if (world.players.size() == world.playersSurroundingExit()) {
//                    Audio.beam.play();
                    scene = 6;
                }
                break;
            case 6:
                loadNewGame("maze2");
                scene = 7;
                break;
            case 7:
                world.tick();
                if (world.players.size() == world.playersSurroundingExit()) {
//                    Audio.trumpet.play();
                    scene = 8;
                }
                break;
            case 8:
                window.setWindowText("Thanks for playing!");
        }
    }

    public void render() {
        screen.clearObjects();
        world.render(screen);
        window.render(screen);
    }
}
