package com.example.lutemongame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
        log.setText("Follow the intense battle and the results from the terminal!");

        System.out.println("Preparing fighters " + lutemonA.getName() + " (" + lutemonA.getColor() + ")" + " and " + lutemonB.getName() + " (" + lutemonB.getColor() + ")");

        System.out.println("The battle begins... \n");

        while (lutemonA.isAlive() && lutemonB.isAlive()) {
            System.out.println("1: " + lutemonA.getName() + " (" + lutemonA.getColor() + ") att: " + lutemonA.getAttack() + "; def: " + lutemonA.getDefense() + "; exp:" + lutemonA.getExperience() + "; health: " + lutemonA.getHealth() + "/" + lutemonA.getMaxHealth() + "\n");
            System.out.println("2: " + lutemonB.getName() + " (" + lutemonB.getColor() + ") att: " + lutemonB.getAttack() + "; def: " + lutemonB.getDefense() + "; exp:" + lutemonB.getExperience() + "; health: " + lutemonB.getHealth() + "/" + lutemonB.getMaxHealth() + "\n");
            System.out.println(lutemonA.getName() + " (" + lutemonA.getColor() + ") attacks " + lutemonB.getName() + "(" + lutemonB.getColor() + ")\n");
            lutemonA.attack(lutemonB);
            if (lutemonB.isAlive()) {
                System.out.println(lutemonB.getName() + " (" + lutemonB.getColor() + ") manages to escape death.\n");
                Lutemon holdA = lutemonA;
                lutemonA = lutemonB;
                lutemonB = holdA;
            } else {
                System.out.println(lutemonB.getName() + " (" + lutemonB.getColor() + ") gets defeated.\n");
                System.out.println(lutemonA.getName() + " (" + lutemonA.getColor() + " ) wins the battle and was rewarded an experience point");
                lutemonA.levelUp();
                lutemonB.addLoss();
                BattleField.getInstance().removeLutemon(lutemonB);
                lutemonB.resetHealth();
                Home.getInstance().addLutemon(lutemonB);
                System.out.println("The battle is over. and both Lutemons are healed.\n");
                System.out.println(lutemonB.getName() + " (" + lutemonB.getColor() + ") was sent back home");
                adapter.notifyDataSetChanged();
                break;
            }
        }
    }
        lutemonA.resetHealth();
        lutemonB.resetHealth();
    }



    public void leaveArena(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
