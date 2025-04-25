package com.example.lutemongame;

public class Home extends LutemonStorage {
    private static Home instance = null;

    private Home(){
    }

    public static Home getInstance() {
        if (instance == null) {
            instance = new Home();
        }
      return instance;
    }

    public Lutemon getLutemonWithId(int id) {
        for (Lutemon lutemon : lutemons) {
            if(lutemon.getId() == id ) {
                return lutemon;
            }
        } return null;
    }



}
