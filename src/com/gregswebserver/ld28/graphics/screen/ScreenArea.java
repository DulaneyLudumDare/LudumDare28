package com.gregswebserver.ld28.graphics.screen;

import com.gregswebserver.ld28.graphics.util.LayeredGraphic;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.Tickable;
import com.gregswebserver.ld28.util.vectors.Vector2i;

import java.util.ArrayList;
import java.util.HashMap;

public class ScreenArea extends LayeredGraphic implements Tickable {

    public HashMap<String, ScreenObject> objects;

    public ScreenArea(Vector2i size, Location location, int layer) {
        super(size, location, layer);
        objects = new HashMap<>();
        clear();
    }

    public void render() {
        clear();
        ArrayList<String> parsedList = new ArrayList<>();
        int activeLayer = 0;
        while (parsedList.size() < objects.size()) {
            for (String name : objects.keySet()) {
                ScreenObject object = objects.get(name);
                if (object.getLayer() == activeLayer && !parsedList.contains(name)) {
                    renderImage(object.getLocation().getPosition().toVector2i(), object);
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

    public void tick() {
        for (ScreenObject object : objects.values()) {
            object.tick();
        }
    }
}