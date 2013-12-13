package com.gregswebserver.ld28.input;

import com.gregswebserver.ld28.game.UsesGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardHandler extends UsesGame implements KeyListener {

    private ArrayList<Integer> keys = new ArrayList<Integer>();

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        keys.add(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        keys.remove(e.getKeyCode());
    }

    public boolean isPressed(int key) {
        return keys.contains(key);
    }
}
