package com.example.lutemongame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainMenuActivity extends AppCompatActivity {
    private Button saveBtn, loadBtn, statsBtn;
    private TextView succesOrFailure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);

        saveBtn = findViewById(R.id.SaveBtn);
        loadBtn = findViewById(R.id.LoadBtn);
        succesOrFailure = findViewById(R.id.textMessage);
        statsBtn = findViewById(R.id.StatsBtn);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        saveBtn.setOnClickListener(v -> {
            String update = SaveAndLoad.saveGame(this);
            succesOrFailure.setText(update);
        });

        loadBtn.setOnClickListener(v -> {
            String update = SaveAndLoad.loadGame(this);
            succesOrFailure.setText(update);
        });


        // Another way to change Activities
        statsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, StatsTabActivity.class);
            startActivity(intent);
        });
    }
    public void switchToAddLutemonActivity(View view) {
        Intent intent = new Intent(this, AddLutemonActivity.class);
        startActivity(intent);
    }
    public void switchToListLutemonActivity(View view) {
        Intent intent = new Intent(this, ListLutemonActivity.class);
        startActivity(intent);
    }
    public void switchToTransferLutemonActivity(View view) {
        Intent intent = new Intent(this, TabActivity.class);
        startActivity(intent);
    }
    public void switchToArena(View view) {
        Intent intent = new Intent(this, BattleArenaActivity.class);
        startActivity(intent);
    }



}