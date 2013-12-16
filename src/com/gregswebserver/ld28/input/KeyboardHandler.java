package com.gregswebserver.ld28.input;

import com.gregswebserver.ld28.game.UsesGame;
import com.gregswebserver.ld28.util.vectors.Vector2d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardHandler extends UsesGame implements KeyListener {

    private ArrayList<String> keys = new ArrayList<>();

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (!isPressed(e.getKeyCode()))
            keys.add("" + e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        keys.remove("" + e.getKeyCode());
    }

    public boolean isPressed(int key) {
        return keys.contains("" + key);
    }

    public Vector2d getArrows() {
        Vector2d sum = new Vector2d();
        if (isPressed(KeyEvent.VK_UP) || isPressed(KeyEvent.VK_W)) sum.add(new Vector2d(0, 1));
        if (isPressed(KeyEvent.VK_RIGHT) || isPressed(KeyEvent.VK_D)) sum.add(new Vector2d(1, 0));
        if (isPressed(KeyEvent.VK_DOWN) || isPressed(KeyEvent.VK_S)) sum.add(new Vector2d(0, -1));
        if (isPressed(KeyEvent.VK_LEFT) || isPressed(KeyEvent.VK_A)) sum.add(new Vector2d(-1, 0));
        return sum;
    }

    public boolean hasKeyPressed() {
        return (keys.size() > 0);
    }
}
