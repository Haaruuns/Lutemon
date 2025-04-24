package com.example.lutemongame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListLutemonActivity extends AppCompatActivity {
    private LutemonStorage lutemonStorage;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_lutemon);

        // lutemonStorage = Home.getInstance(); doesn't work after moving the lutemons to training or battle
        ArrayList<Lutemon> allLutemons = new ArrayList<>();
        allLutemons.addAll(Home.getInstance().getLutemons());
        allLutemons.addAll(TrainingArea.getInstance().getLutemons());
        allLutemons.addAll(BattleField.getInstance().getLutemons());
        recyclerView = findViewById(R.id.ListLutemonRV);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new LutemonAdapter(getApplicationContext(), allLutemons));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void switchBackToMainMenuActivity2(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
    public void healLutemon(View view) {

    }
}