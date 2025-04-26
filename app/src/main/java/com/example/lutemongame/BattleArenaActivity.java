package com.example.lutemongame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class BattleArenaActivity extends AppCompatActivity {
    private RecyclerView rv;

    private BattleField storage;
    private BattleArenaAdapter adapter;
    private TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_battle_arena);

        storage = BattleField.getInstance();
        rv = findViewById(R.id.RV);

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BattleArenaAdapter(getApplicationContext(), storage.getLutemons());
        rv.setAdapter(adapter);

        MusicManager.startBattleMusic(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void beginBattle(View view) {
        ArrayList<Lutemon> selected = adapter.getSelected();
        log = findViewById(R.id.Log);
        if (selected.size() != 2) {
            log.setText("Please select 2 fighters!");
            return;
        }
        Lutemon lutemonA = selected.get(0);
        Lutemon lutemonB = selected.get(1);
        log.setText("Welcome to the Lutemon Battle Arena! ");
        log.append("The most fierce and dangerous arena in the world of Lutemons, only the most notorious are known to survive...\n\n");
        log.append("Today's brave warriors are:\n");
        log.append("1. Warrior: " + lutemonA.getName() + " (" + lutemonA.getColor() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefense() + "; exp:" + lutemonA.getExperience() + "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth() + "\n");
        log.append("2. Warrior: " + lutemonB.getName() + " (" + lutemonB.getColor() + ") att: " + lutemonB.getAttack() + "; def: " + lutemonB.getDefense() + "; exp:" + lutemonB.getExperience() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth() + "\n\n");

        log.append("Preparing fighters " + lutemonA.getName() + " (" + lutemonA.getColor() + ")" + " VS. " + lutemonB.getName() + " (" + lutemonB.getColor() + ")\n\n");

        log.append("Let the battle begin! \n\n");

        while (lutemonA.isAlive() && lutemonB.isAlive()) {
            log.append(getColorSkill(lutemonA, lutemonB));
            int damage = lutemonA.attack(lutemonB);
            Random rand = new Random();
            boolean randomDodge = rand.nextBoolean();
            if (damage == 0) {
                if (randomDodge) {
                    log.append(lutemonB.getName() + " managed to dodge the fierce attack with the power of MIGHT!\n");
                } else {
                    log.append(lutemonB.getName() + " absorbed " + lutemonA.getName() + " attempt of an attack and replied with " + lutemonB.getName() + ": Is that all you've got? Pathetic.\n ");
                }

            } else {
                log.append(lutemonB.getName() + " took " + damage + " HP of damage.\n\n");
            }
            log.append("1: " + lutemonA.getName() + " (" + lutemonA.getColor() + ") health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth() + "\n");
            log.append("2: " + lutemonB.getName() + " (" + lutemonB.getColor() + ") health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth() + "\n\n");
            if (lutemonB.isAlive()) {
                log.append(lutemonB.getName() + " (" + lutemonB.getColor() + ") manages to escape death.\n\n");
                Lutemon holdA = lutemonA;
                lutemonA = lutemonB;
                lutemonB = holdA;
            } else {
                log.append(lutemonB.getName() + " (" + lutemonB.getColor() + ") gets defeated.\n");
                log.append(lutemonA.getName() + " (" + lutemonA.getColor() + ") wins the battle and was rewarded with an experience point\n");
                lutemonA.levelUp();
                lutemonB.addLoss();
                BattleField.getInstance().removeLutemon(lutemonB);
                lutemonB.resetHealth();
                Home.getInstance().addLutemon(lutemonB);
                log.append("The battle is over, and both Lutemons are healed.\n");
                log.append(lutemonB.getName() + " (" + lutemonB.getColor() + ") was sent back home!\n");
                adapter.notifyDataSetChanged();
                break;
            }
        }
        lutemonA.resetHealth();
        lutemonB.resetHealth();
        adapter.clearList();
    }
    private String getColorSkill(Lutemon lutemonA, Lutemon lutemonB) {
        Random rand = new Random();
        boolean choice = rand.nextBoolean();
        switch (lutemonA.getColor()) {
            case "White":
                if (choice) {
                    return (lutemonA.getName() + " freezes " + lutemonB.getName() + " with an crystallized snowstorm!\n\n");
            } else {
                    return (lutemonA.getName() + " throws snowball with the temperature of absolute zero at " + lutemonB.getName() + "!\n\n");
                }
            case "Orange":
                if (choice) {
                    return (lutemonA.getName() + " ignites into flames and charges like a fireball towards" + lutemonB.getName() + "!\n\n");
                } else {
                    return (lutemonA.getName() + " goes supernova and strikes "+ lutemonB.getName() +" with the power of the sun!\n\n");
                }
            case "Green":
                if (choice) {
                    return (lutemonA.getName() + " strikes "+ lutemonB.getName() + " with the fury of Mother nature!\n\n");
                } else {
                    return (lutemonA.getName() + " creates an earthquake and traps " + lutemonB.getName() + " under magma!\n\n");
                }
            case "Black":
                if (choice) {
                    return (lutemonA.getName() + " vanishes into the darkness and strikes a devasting blow at " + lutemonB.getName() + " from the shadows!\n\n");
                } else {
                    return (lutemonA.getName() + " spawns an fiercy army of bats to attack " + lutemonB.getName() + ", uh oh.\n\n");
                }
            case "Pink":
                if (choice) {
                    return (lutemonA.getName() + " uses a love spell to make " + lutemonB.getName() + " fall in love, and then friendzones them!\n\n");
                } else {
                    return (lutemonA.getName() + " hypnotises and tricks " + lutemonB.getName() + " to tell their most embarrassing secrets!\n\n");
                }

        }
        return "";
    }


    public void leaveArena(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
        MusicManager.stopBattleMusic();
        MusicManager.startBackground(this);
    }
}

