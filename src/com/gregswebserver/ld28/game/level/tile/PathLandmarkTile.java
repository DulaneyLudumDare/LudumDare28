package com.gregswebserver.ld28.game.level.tile;

import com.gregswebserver.ld28.graphics.sprite.Sprite;
import com.gregswebserver.ld28.util.vectors.Vector2d;

import java.util.Random;

public class PathLandmarkTile extends PathTile {

    private Sprite feature;

    public PathLandmarkTile(Vector2d position) {
        super(position);
        feature = getRandomSprite();
    }

    public Sprite getFeature(){
        return feature;
    }

    public static Sprite getRandomSprite() {
        Random rand = new Random();
        switch (rand.nextInt(2)) {
            case 0:
                return Sprite.landmark_crack;
            default:
                return Sprite.landmark_skull;
        }
    }
}