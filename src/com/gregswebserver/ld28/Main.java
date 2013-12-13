package com.gregswebserver.ld28;

import com.gregswebserver.ld28.game.Game;
import com.gregswebserver.ld28.game.UsesGame;
import com.gregswebserver.ld28.graphics.Screen;
import com.gregswebserver.ld28.util.Clock;

public class Main implements Runnable {

    private boolean isRunning = false;
    public Screen screen;
    private Clock updateTime;
    private Clock frameTime;
    private int updates;
    private int frames;
    public Game game;

    public Main() {
        isRunning = false;
        screen = new Screen();
        updateTime = new Clock(60); // 60 updates per second
        frameTime = new Clock(1); // 1 tick per second
        updates = 0;
        frames = 0;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    public synchronized void start() {
        game = new Game(screen);
        UsesGame.setGame(game);
        new Thread(this, "Main").start();
        isRunning = true;
    }

    public synchronized void stop() {
        isRunning = false;
    }

    public void run() {
        while (isRunning) {
            if (updateTime.getTimer()) {
                //Game engine updates
                screen.update();
                updates++;
                updateTime.resetTimer();
            }
            //Render engine updates
            game.update();
            game.render();
            screen.render();
            frames++;
            if (frameTime.getTimer()) {
                screen.setWindowText("UPS: " + updates + " FPS: " + frames);
                updates = frames = 0;
                frameTime.resetTimer();
            }
        }
    }
}