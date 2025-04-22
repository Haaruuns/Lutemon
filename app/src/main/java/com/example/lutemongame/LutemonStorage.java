package com.example.lutemongame;

import java.util.ArrayList;

public class LutemonStorage {
    protected ArrayList<Lutemon> lutemons = new ArrayList<>();

    public ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }

    public void addLutemon(Lutemon lutemon) {
        lutemons.add(lutemon);
    }
    // Haluanko myöhemmin poistaa lutemonin jos haluan teen removeLutemon metodin

}
