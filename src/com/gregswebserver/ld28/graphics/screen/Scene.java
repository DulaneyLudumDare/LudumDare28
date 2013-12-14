package com.gregswebserver.ld28.graphics.screen;

import java.util.HashMap;

public class Scene {

    private HashMap<String, ScreenArea> areas;

    public Scene() {
        areas = new HashMap<>();
    }

    public HashMap<String, ScreenArea> getAreas() {
        return areas;
    }

    public void deleteArea(String name) {
        if (areas.containsKey(name)) {
            areas.remove(name);
        }
    }

    public void addArea(String name, ScreenArea area) {
        areas.put(name, area);
    }
}
