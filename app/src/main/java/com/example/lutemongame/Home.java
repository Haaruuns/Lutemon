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



}
