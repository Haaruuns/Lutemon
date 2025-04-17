package com.example.lutemongame;

import java.util.ArrayList;

public class LutemonStorage {
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private static LutemonStorage lutemonStorage = null;

    private LutemonStorage(){
    }

    public static LutemonStorage getInstance() {
        if(lutemonStorage == null) {
            lutemonStorage = new LutemonStorage();
        }
        return lutemonStorage;
    }

    public ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }

    public void addLutemon(Lutemon lutemon) {
        lutemons.add(lutemon);
    }
    // Haluanko myöhemmin poistaa lutemonin jos haluan teen removeLutemon metodin

}
