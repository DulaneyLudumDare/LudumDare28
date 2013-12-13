package com.gregswebserver.ld28.graphics;

import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2i;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

public class Screen extends Canvas {
    private static final long serialVersionUID = 1L;

    private static boolean debug = false;
    private static int WIDTH = 300;
    private static int HEIGHT = WIDTH * 9 / 16;
    private static int SCALE = 2;
    private static int BGCOLOR = 0x60A0FF;
    private static String TITLE = "Game AI Simulator";

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private JFrame frame;
    private String suffix = "";
    public ArrayList<ScreenArea> areas;

    public Screen() {
        frame = new JFrame();
        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setPreferredSize(size);
        frame.setResizable(false);
        updateWindowText();
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        areas = new ArrayList<ScreenArea>();
        clear();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        clear();
        for (ScreenArea area : areas) {
            area.render();
            for (int x = 0; x < area.size.getX(); x++) {
                int xx = area.location.getX() + x;
                for (int y = 0; y < area.size.getY(); y++) {
                    int yy = area.location.getY() + y;
                    if (xx >= WIDTH || xx < 0 || yy >= HEIGHT || yy < 0) continue;
                    int col = area.pixels[x + y * area.size.getX()];
                    if (col != 0xffff00ff || debug) pixels[xx + yy * WIDTH] = col;
                }
            }
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = BGCOLOR;
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
        frame.setTitle(TITLE + suffix);
    }

    public void update() {
        for (ScreenArea area : areas) {
            area.location.tick();
            area.lifetime.tick();
            area.render();
        }
        checkDeath();
    }

    public void clearAreas() {
        areas.clear();
        ScreenArea.nextID = 0;
        if (debug) System.out.println("ClearedAreas");
    }

    public int addArea(Vector2i size, Location position) {
        ScreenArea area = new ScreenArea(size, position);
        areas.add(area);
        if (debug) System.out.println("AddedArea " + area.id);
        return area.id;
    }

    public void deleteArea(int id) {
        int index = getIndex(id);
        if (index == -1) return;
        areas.remove(index);
        if (debug) System.out.println("DeletedArea " + id);
    }

    public int getIndex(int id) {
        for (int i = 0; i < areas.size(); i++) {
            if (areas.get(i).id == id) return i;
        }
        return -1;
    }

    private void checkDeath() {
        for (int i = 0; i < areas.size(); i++) {
            if (areas.get(i).lifetime.isDead()) {
                areas.remove(i);
                checkDeath();
                return;
            }
        }
    }
}
