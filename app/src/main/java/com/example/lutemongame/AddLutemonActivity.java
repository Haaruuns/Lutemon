package com.example.lutemongame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddLutemonActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private EditText lutemonName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_lutemon);

        radioGroup = findViewById(R.id.LutemonTypeRadioGroup);
        lutemonName = findViewById(R.id.LutemonNameEdit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void addLutemon(View view) {
        String name = lutemonName.getText().toString();
        int id = radioGroup.getCheckedRadioButtonId();
        if(id == R.id.BlackRadioButton) {
            Black black = new Black(name);
            LutemonStorage.getInstance().addLutemon(black);
        } else if (id == R.id.WhiteRadioButton) {
            White white = new White(name);
            LutemonStorage.getInstance().addLutemon(white);
        } else if (id == R.id.GreenRadioButton) {
            Green green = new Green(name);
            LutemonStorage.getInstance().addLutemon(green);
        } else if (id == R.id.PinkRadioButton) {
            Pink pink = new Pink(name);
            LutemonStorage.getInstance().addLutemon(pink);
        } else {
            Orange orange = new Orange(name);
            LutemonStorage.getInstance().addLutemon(orange);
        }
    }

    public void switchBackToMainMenuActivity(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}