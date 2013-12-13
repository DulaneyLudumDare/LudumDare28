package com.gregswebserver.ld28.util;

public class Lifetime {

    public int ticksAlive;
    public int ticksToDeath;

    public Lifetime() {
        ticksAlive = 0;
        ticksToDeath = 0;
    }

    public void tick() {
        ticksAlive++;
    }

    public void setLife(int ticks) {
        ticksToDeath = ticks;
    }

    public boolean isDead() {
        if (ticksToDeath == 0) return false;
        if (ticksAlive > ticksToDeath) return true;
        return false;
    }
}
