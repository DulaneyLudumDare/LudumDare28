package com.gregswebserver.ld28.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {

    public static Audio beam = new Audio("/audio/beam.wav");
    public static Audio step1 = new Audio("/audio/step1.wav");
    public static Audio step2 = new Audio("/audio/step2.wav");

    private static String path;

    public Audio(String path) {
        this.path = path;
    }

    public static synchronized void playSound() {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Audio.class.getResourceAsStream(path));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
