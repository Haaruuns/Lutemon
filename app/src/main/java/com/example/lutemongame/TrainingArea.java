package com.example.lutemongame;

import android.content.Context;

public class TrainingArea extends LutemonStorage{
    private static TrainingArea instance = null;

    private TrainingArea(){
    }

    public static TrainingArea getInstance() {
        if (instance == null) {
            instance = new TrainingArea();
        }
        return instance;
    }


}
