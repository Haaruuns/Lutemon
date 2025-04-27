package com.example.lutemongame;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SaveAndLoad {

    public static class TheWholeList implements Serializable {
        private ArrayList<Lutemon> homeLutemons, battleFieldLutemons, trainingAreaLutemons;
        public ArrayList<Lutemon> getHomeLutemons() {
            return homeLutemons;
        }
        public ArrayList<Lutemon> getBattleFieldLutemons() {
            return battleFieldLutemons;
        }
        public ArrayList<Lutemon> getTrainingAreaLutemons() {
            return trainingAreaLutemons;
        }
        public void setHomeLutemons(ArrayList<Lutemon> homeLutemons) {
            this.homeLutemons = homeLutemons;
        }
        public void setBattleFieldLutemons(ArrayList<Lutemon> battleFieldLutemons) {
            this.battleFieldLutemons = battleFieldLutemons;
        }
        public void setTrainingAreaLutemons(ArrayList<Lutemon> trainingAreaLutemons) {
            this.trainingAreaLutemons = trainingAreaLutemons;
        }
    }

    public static String saveGame(Context context) {
        if(Home.getInstance().getLutemons().isEmpty() && TrainingArea.getInstance().getLutemons().isEmpty()
        && BattleField.getInstance().getLutemons().isEmpty()) {
            return "No Lutemons to save.";
        }

        TheWholeList theWholeList = new TheWholeList();
        theWholeList.setHomeLutemons(Home.getInstance().getLutemons());
        theWholeList.setTrainingAreaLutemons(TrainingArea.getInstance().getLutemons());
        theWholeList.setBattleFieldLutemons(BattleField.getInstance().getLutemons());
        try {
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemon.data",Context.MODE_PRIVATE));
            lutemonWriter.writeObject(theWholeList);
            lutemonWriter.close();
            return "Game saved successfully!";
        } catch (IOException e) {
            return "Failed to save the game.";
        }
    }

    public static String loadGame(Context context) {
        try {
            ObjectInputStream lutemonReader = new ObjectInputStream(context.openFileInput("lutemon.data"));
            TheWholeList theWholeList =  (TheWholeList) lutemonReader.readObject();
            lutemonReader.close();
            Home.getInstance().lutemons = theWholeList.getHomeLutemons();
            TrainingArea.getInstance().lutemons = theWholeList.getTrainingAreaLutemons();
            BattleField.getInstance().lutemons = theWholeList.getBattleFieldLutemons();
            return "Game loaded successfully!";
        } catch (FileNotFoundException e) {
            return "No saved game found.";
        } catch (IOException | ClassNotFoundException e) {
            return "Failed to load the game.";
        }

    }

}