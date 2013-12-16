package com.gregswebserver.ld28.graphics.screen;

import com.gregswebserver.ld28.graphics.util.LayeredGraphic;
import com.gregswebserver.ld28.util.vectors.Vector2i;

import java.util.ArrayList;
import java.util.HashMap;

public class Screen extends LayeredGraphic {

    private HashMap<String, ScreenObject> objects;

    public Screen(Vector2i size) {
        super(size, new Vector2i(), 0);
        objects = new HashMap<>();
        clear();
    }

    public void render() {
        clear();
        ArrayList<String> parsedList = new ArrayList<>();
        int activeLayer = 0;
        while (parsedList.size() < objects.size()) {
            for (String name : objects.keySet()) {
                ScreenObject area = objects.get(name);
                if (area.getLayer() == activeLayer && !parsedList.contains(name)) {
                    renderImage(area.getPosition(), area);
                    parsedList.add(name);
                }
            }
            activeLayer++;
        }
    }

    public void clearObjects() {
        objects.clear();
    }

    public void addObject(String name, ScreenObject object) {
        objects.put(name, object);
    }

    public void deleteObject(String name) {
        if (objects.containsKey(name)) {
            objects.remove(name);
        }
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }
}
