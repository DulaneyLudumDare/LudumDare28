package com.gregswebserver.ld28.graphics.screen;

import com.gregswebserver.ld28.graphics.util.LayeredGraphic;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2i;

import java.util.ArrayList;
import java.util.HashMap;

public class Screen extends LayeredGraphic {

    private HashMap<String, ScreenArea> areas;

    public Screen(Vector2i size) {
        super(size, new Location(), 0);
        areas = new HashMap<>();
        clear();
    }

    public void render() {
        clear();
        ArrayList<String> parsedList = new ArrayList<>();
        int activeLayer = 0;
        while (parsedList.size() < areas.size()) {
            System.out.println("layer " + activeLayer);
            for (String name : areas.keySet()) {
                ScreenArea area = areas.get(name);
                if (area.getLayer() == activeLayer && !parsedList.contains(name)) {
                    System.out.println(" area " + name);
                    System.out.println(" size " + area.size.toString());
                    renderImage(area.getLocation().getPosition().toVector2i(), area);
                    parsedList.add(name);
                }
            }
            activeLayer++;
        }
    }

    public void deleteArea(String name) {
        if (areas.containsKey(name)) {
            areas.remove(name);
        }
    }

    public void addArea(String name, ScreenArea area) {
        areas.put(name, area);
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
