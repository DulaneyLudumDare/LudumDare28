package com.gregswebserver.ld28.graphics.menu;

import com.gregswebserver.ld28.graphics.Screen;
import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.graphics.sprite.SpriteText;
import com.gregswebserver.ld28.util.Location;
import com.gregswebserver.ld28.util.vectors.Vector2d;
import com.gregswebserver.ld28.util.vectors.Vector2i;

public class MenuElement {

    public int id;
    private Location position;
    private Sprite sprite;
    private String text;
    private int spriteScreenID;
    private int textScreenID;
    private int menuElementID;

    public MenuElement() {
        id = 0;
        sprite = null;
        position = null;
        text = "";
        menuElementID = -1;
        spriteScreenID = -1;
        textScreenID = -1;
    }

    public MenuElement(int id, Location position, Sprite sprite, String text) {
        this.id = id;
        this.sprite = sprite;
        this.position = position;
        this.text = text;
        menuElementID = -1;
        spriteScreenID = -1;
        textScreenID = -1;
    }

    public void render(Screen screen) {
        if (menuElementID == -1) screen.addArea(new Vector2i(200), new Location());
        int i = screen.getIndex(menuElementID);
        spriteScreenID = screen.areas.get(i).addObject(position.getX(), position.getY(), sprite);
        textScreenID = screen.areas.get(i).addObject(position.getX(), position.getY(), new SpriteText(text, 16));
    }

    public void clear(Screen screen) {
        int i = screen.getIndex(menuElementID);
        screen.areas.get(i).deleteObject(spriteScreenID);
        screen.areas.get(i).deleteObject(textScreenID);
    }

}
