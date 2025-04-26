package com.example.lutemongame;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicManager {
    private static MediaPlayer gameAudio;
    private static Boolean toggle = true;
    private static MediaPlayer battleMusic;

    public static void startBackground(Context context) {
        if (toggle) {
            gameAudio = MediaPlayer.create(context, R.raw.audio);
            gameAudio.setLooping(true);
            gameAudio.start();
        }
    }

    public static void startBattleMusic(Context context) {
        if (toggle) {
            battleMusic = MediaPlayer.create(context, R.raw.battle);
            battleMusic.setLooping(true);
            battleMusic.start();
        }
    }


    public static void stopMusic() {
        gameAudio.stop();
    }
    public static void stopBattleMusic() {
        if (toggle) {
            battleMusic.stop();
        }
    }
    public static void toggleMusic() {
        if (toggle) {
            gameAudio.stop();
            toggle = false;
        } else {
            toggle = true;
        }
        }
    public static boolean returnToggle() {
        return toggle;
    }
}

