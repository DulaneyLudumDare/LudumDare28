package com.gregswebserver.ld28.graphics;

import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.util.Lifetime;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2d;

public class ScreenObject {

    public Location location;
    public Sprite sprite;
    public int id;
    public Lifetime lifetime;
    public static int nextID = 0;

    public ScreenObject() {
        location = new Location();
        sprite = new Sprite();
        lifetime = new Lifetime();
        setID();
    }

    public ScreenObject(int x, int y, Sprite sprite) {
        location = new Location(new Vector2d(x, y));
        this.sprite = sprite;
        lifetime = new Lifetime();
        setID();
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    private void setID() {
        id = nextID;
        nextID++;
    }

    public boolean inHitbox(int x, int y) {
        if (x < location.getX() || x > (location.getX() + sprite.size.getX())) return false;
        if (y < location.getY() || y > (location.getY() + sprite.size.getY())) return false;
        return true;
    }
}
