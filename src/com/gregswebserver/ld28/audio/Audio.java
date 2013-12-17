package com.gregswebserver.ld28.audio;

import javax.sound.sampled.*;
import java.io.IOException;

public class Audio {

    public static Audio beam = new Audio("/audio/beam.wav");                    //end level
    public static Audio step1 = new Audio("/audio/step_1.wav");                 //walking
    public static Audio step2 = new Audio("/audio/step_2.wav");                 //walking
    public static Audio trumpet = new Audio("/audio/trumpet.wav");              //meet up accomplishment
    public static Audio switch_player = new Audio("/audio/switch_player.wav");  //changing player focus

    public static Audio dripping = new Audio("/audio/dripping.wav");            //background ambiance
    public static Audio bats = new Audio("/audio/bats.wav");
    public static Audio cave_sound_1 = new Audio("/audio/cave_sound_1.wav");
    public static Audio cave_sound_2 = new Audio("/audio/cave_sound_2.wav");
    public static Audio cave_sound_3 = new Audio("/audio/cave_sound_3.wav");
    public static Audio rocks = new Audio("/audio/rocks.wav");


    private AudioInputStream aInStream;
    private Clip clip;
    private float defaultVolume;
    private FloatControl gainControl;

    public Audio(String path) {
        load(path);
    }

    public void load(String path) {
        try {
            aInStream = AudioSystem.getAudioInputStream(Audio.class.getResourceAsStream(path));
            clip = AudioSystem.getClip();
            clip.open(aInStream);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public void loop() {
        if (!clip.isActive()) {
            clip.setMicrosecondPosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }

    public void stop() {
        clip.stop();
    }

    public void close() {
        clip.close();
    }

    public void setVolume(float db) {
        if (db > -80.0f && db <= 6.0f)
            gainControl.setValue(db);
        else
            gainControl.setValue(0.0f);
    }
}
