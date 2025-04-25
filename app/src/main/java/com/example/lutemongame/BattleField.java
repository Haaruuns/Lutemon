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


}
