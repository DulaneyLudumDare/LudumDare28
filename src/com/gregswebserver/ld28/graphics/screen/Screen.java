package com.gregswebserver.ld28.graphics.screen;

import com.gregswebserver.ld28.graphics.util.Graphic;
import com.gregswebserver.ld28.util.vectors.Vector2i;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.HashMap;

public class Screen extends Canvas {
    private static final long serialVersionUID = 1L;

    public final Vector2i size = new Vector2i(640, 480);
    public final String title = "Sight";
    private String suffix = "";

    private BufferedImage image = new BufferedImage(size.getX(), size.getY(), BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private JFrame frame;
    private HashMap<String, ScreenArea> areas;

    public Screen() {
        frame = new JFrame();
        setPreferredSize(new Dimension(size.getX(), size.getY()));
        frame.setResizable(false);
        updateWindowText();
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        areas = new HashMap<>();
        clear();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        clear();
        ArrayList<String> parsedList = new ArrayList<>();
        int activeLayer = 0;
        while (parsedList.size() < areas.size()) {
            for (String name : areas.keySet()) {
                ScreenArea area = areas.get(name);
                if (area.getLayer() == activeLayer && !parsedList.contains(name)) {
                    renderImage(area.getPosition(), area);
                    parsedList.add(name);
                }
            }
            activeLayer++;
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    public void deleteArea(String name) {
        if (areas.containsKey(name)) {
            areas.remove(name);
        }
    }

    public void addArea(String name, ScreenArea area) {
        areas.put(name, area);
    }

    protected void renderImage(Vector2i position, Graphic image) {
        for (int x = 0; x < image.size.getX(); x++) {
            int xx = position.getX() + x;
            for (int y = 0; y < image.size.getY(); y++) {
                int yy = position.getY() + y;
                if (xx >= position.getX() || xx < 0 || yy >= position.getY() || yy < 0)
                    continue;
                int col = image.pixels[x + (y * image.size.getX())];
                pixels[xx + (yy * size.getX())] = col;
            }
        }
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0x60A0FF;
        }
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

    public void update() {
        for (ScreenArea area : areas.values()) {
            area.tick();
        }
    }

    public void loadScene(Scene scene) {
        areas = scene.getAreas();
    }
}
