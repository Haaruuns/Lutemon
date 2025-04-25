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
    public void removeLutemon(Lutemon lutemon) {
        lutemons.remove(lutemon);
    }

    public Lutemon getLutemonWithId(int id) {
        for(Lutemon lutemon : lutemons) {
            if (lutemon.getId() == id) {
                return lutemon;
            }
        } return null;
    }

}
