package com.example.lutemongame;

public class BattleField extends LutemonStorage {
    private static BattleField instance = null;

    private BattleField(){
    }

    public static BattleField getInstance() {
        if (instance == null) {
            instance = new BattleField();
        }
        return instance;
    }



    public Lutemon getLutemonWithId(int id) {
        for(Lutemon lutemon : lutemons) {
            if (lutemon.getId() == id) {
                return lutemon;
            }
        } return null;
    }

    public void removeLutemon(Lutemon lutemon) {
        lutemons.remove(lutemon);
    }}
