package com.gregswebserver.ld28.graphics.screen;

import com.gregswebserver.ld28.input.KeyboardHandler;
import com.gregswebserver.ld28.util.vectors.Vector2i;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Window extends Canvas {
    private static final long serialVersionUID = 1L;

    public KeyboardHandler keyboard = new KeyboardHandler();

    public final Vector2i size;
    public final int scale;
    public final String title = "Sight";
    private String suffix = "";

    private BufferedImage image;
    private int[] pixels;
    private JFrame frame;

    public Window(Vector2i size, int scale) {
        this.scale = scale;
        this.size = size;
        image = new BufferedImage(size.getX(), size.getY(), BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        frame = new JFrame();
        setPreferredSize(new Dimension(size.getX() * scale, size.getY() * scale));
        addKeyListener(keyboard);
        frame.setResizable(false);
        updateWindowText();
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void render(Screen screen) {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.render();
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    public void setWindowText(String text) {
        this.suffix = " | " + text;
        updateWindowText();
    }

    public void clearWindowText() {
        suffix = "";
        updateWindowText();
    }

    public void updateWindowText() {
        frame.setTitle(title + suffix);
    }
}
