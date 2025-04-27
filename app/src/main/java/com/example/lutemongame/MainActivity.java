package com.example.lutemongame;

import static kotlin.random.RandomKt.Random;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button startGame;
    private ProgressBar progressBar;
    private TextView tipText;
    private TextView loading;

    private String[] tips = {
            "Tip: Fight with your Lutemon to gain experience levels!",
            "Tip: There are different color Lutemons, each color has its own unique set of skills!",
            "Tip: You can check the statistics of your Lutemon from the menu!",
            "Tip: Send your Lutemon to training to boost its experience!",
            "Tip: You can save your progress anytime from the main menu"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        MusicManager.startBackground(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void startLoading(View view) {
        progressBar = findViewById(R.id.progressBar);
        tipText = findViewById(R.id.TipText);
        startGame = findViewById(R.id.startGame);
        loading = findViewById(R.id.Loading);


        progressBar.setVisibility(View.VISIBLE);
        tipText.setVisibility(View.VISIBLE);
        startGame.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        Random number = new Random();
        int tipNumber = number.nextInt(3);
        tipText.setText(tips[tipNumber]);

        new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                int finalI = i;
                runOnUiThread(() -> progressBar.setProgress(finalI));
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            runOnUiThread(() -> switchToMainMenuActivity());
        }).start();
    }

    public void switchToMainMenuActivity() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
