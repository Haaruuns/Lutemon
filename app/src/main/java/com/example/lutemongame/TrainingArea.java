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



    public Lutemon getLutemonWithId(int id) {
        for (Lutemon lutemon : lutemons) {
            if (lutemon.getId() == id) {
                return lutemon;
            }
        } return null;
    }
    public void removeLutemon(Lutemon lutemon) {
        lutemons.remove(lutemon);
    }
}
