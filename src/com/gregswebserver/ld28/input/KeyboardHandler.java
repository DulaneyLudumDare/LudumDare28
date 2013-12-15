package com.gregswebserver.ld28.input;

import com.gregswebserver.ld28.game.UsesGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardHandler extends UsesGame implements KeyListener {

    private ArrayList<String> keys = new ArrayList<>();

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        keys.add("" + e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        keys.remove("" + e.getKeyCode());
    }

    public boolean isPressed(int key) {
        return keys.contains("" + key);
    }

    public int getArrowDir() {
        if (isPressed(KeyEvent.VK_UP) || isPressed(KeyEvent.VK_W)) return 1;
        else if (isPressed(KeyEvent.VK_RIGHT) || isPressed(KeyEvent.VK_D)) return 2;
        else if (isPressed(KeyEvent.VK_DOWN) || isPressed(KeyEvent.VK_S)) return 3;
        else if (isPressed(KeyEvent.VK_LEFT) || isPressed(KeyEvent.VK_A)) return 4;
        else return 0;
    }
}
