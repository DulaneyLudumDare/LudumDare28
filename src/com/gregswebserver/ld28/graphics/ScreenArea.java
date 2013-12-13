package com.gregswebserver.ld28.graphics;

import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.util.Lifetime;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.Tickable;
import com.gregswebserver.ld28.util.vectors.Vector2d;
import com.gregswebserver.ld28.util.vectors.Vector2i;

import java.util.ArrayList;

public class ScreenArea extends Graphic implements Tickable {

    public ArrayList<ScreenObject> objects;
    public Lifetime lifetime;
    public Location location;
    public int id;
    public static int nextID = 0;

    public ScreenArea(Vector2i size, Location location) {
        super(size);
        this.location = location;
        objects = new ArrayList<ScreenObject>();
        lifetime = new Lifetime();
        clear();
        setID();
    }

    public void render() {
        clear();
        game.debug.printDebug(5, "AreaID " + id + " Objects " + objects.size());
        for (int i = objects.size() - 1; i >= 0; i--) {
            ScreenObject object = objects.get(i);
            renderImage(object.location.getPosition(), object.sprite);
        }
    }

    private void renderImage(Vector2i pos, Sprite sprite) {
        for (int x = 0; x < sprite.size.getX(); x++) {
            int xx = pos.getX() + x;
            for (int y = 0; y < sprite.size.getY(); y++) {
                int yy = pos.getY() + y;
                if (xx >= pos.getX() || xx < 0 || yy >= pos.getY() || yy < 0) continue;
                int col = sprite.pixels[x + (y * sprite.size.getX())];
                pixels[xx + (yy * size.getX())] = col;
            }
        }
    }

    public int getIndex(int id) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).id == id) return i;
        }
        return -1;
    }

    public void clearObjects() {
        objects.clear();
        ScreenObject.nextID = 0;
        game.debug.printDebug(4, "ClearedObjects " + id);
    }

    public int addObject(int x, int y, Sprite sprite) {
        ScreenObject object = new ScreenObject(x, y, sprite);
        objects.add(object);
        game.debug.printDebug(4, "AddedObject " + object.id);
        return object.id;
    }

    public void deleteObject(int id) {
        int index = getIndex(id);
        if (index == -1) return;
        objects.remove(index);
        game.debug.printDebug(4, "DeletedObject " + id);
    }

    public void setPos(int id, Vector2d vec) {
        int index = getIndex(id);
        if (index == -1) return;
        objects.get(index).location.setPos(vec);
    }

    public void setVel(int id, Vector2d vec) {
        int index = getIndex(id);
        if (index == -1) return;
        objects.get(index).location.setVel(vec);
    }

    public void setAcc(int id, Vector2d vec) {
        int index = getIndex(id);
        if (index == -1) return;
        objects.get(index).location.setAcc(vec);
    }

    public void tick() {
        for (ScreenObject object : objects) {
            object.location.tick();
            object.lifetime.tick();
        }
        checkDeath();
    }

    private void checkDeath() {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).lifetime.isDead()) {
                objects.remove(i);
                checkDeath();
                return;
            }
        }
    }

    private void setID() {
        id = nextID;
        nextID++;
    }
}