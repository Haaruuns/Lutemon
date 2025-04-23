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
        public ArrayList<Lutemon> homeLutemons, battleFieldLutemons, trainingAreaLutemons;
    }

    public static String saveGame(Context context) {
        if(Home.getInstance().getLutemons().isEmpty() && TrainingArea.getInstance().getLutemons().isEmpty()
        && BattleField.getInstance().getLutemons().isEmpty()) {
            context.deleteFile("lutemon.data");
            // deletes old file if there is nothing to save.
            // ASK dekan if this is a good idea
            return "No Lutemons to save.";
        }

        TheWholeList theWholeList = new TheWholeList();
        theWholeList.homeLutemons = Home.getInstance().getLutemons();
        theWholeList.trainingAreaLutemons = TrainingArea.getInstance().getLutemons();
        theWholeList.battleFieldLutemons = BattleField.getInstance().getLutemons();
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
            Home.getInstance().lutemons = theWholeList.homeLutemons;
            TrainingArea.getInstance().lutemons = theWholeList.trainingAreaLutemons;
            BattleField.getInstance().lutemons = theWholeList.battleFieldLutemons;
            return "Game loaded successfully!";
        } catch (FileNotFoundException e) {
            return "No saved game found.";
        } catch (IOException | ClassNotFoundException e) {
            return "Failed to load the game.";
        }

    }

}