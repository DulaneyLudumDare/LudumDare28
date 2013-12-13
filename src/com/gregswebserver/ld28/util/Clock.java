package com.gregswebserver.ld28.util;


public class Clock {

    private boolean debug = false;
    private double initTime;
    private double lastTime;
    private double sPerDiv;

    private final double ms = 1000.0;

    public Clock(double ticksPerSecond) {
        sPerDiv = 1 / ticksPerSecond;
        initTime = System.currentTimeMillis() / ms;
        lastTime = getSeconds();
    }

    public boolean getTimer() {
        return !((lastTime + sPerDiv) > getSeconds());
    }

    public void resetTimer() {
        while (getTimer()) {
            lastTime += sPerDiv;
        }
    }

    private double getSeconds() {
        return ((System.currentTimeMillis() / ms) - initTime);
    }
}
