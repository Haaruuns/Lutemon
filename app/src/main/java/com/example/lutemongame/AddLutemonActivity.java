package com.example.lutemongame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddLutemonActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private EditText lutemonName;
    private TextView errorMessage, successMessage;
    private ImageView whiteLutemon, blackLutemon, orangeLutemon, pinkLutemon, greenLutemon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_lutemon);

        radioGroup = findViewById(R.id.LutemonTypeRadioGroup);
        lutemonName = findViewById(R.id.LutemonNameEdit);
        errorMessage = findViewById(R.id.Fail);
        successMessage = findViewById(R.id.Success);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                blackLutemon = findViewById(R.id.BlackLutemon);
                whiteLutemon = findViewById(R.id.WhiteLutemon);
                greenLutemon = findViewById(R.id.GreenLutemon);
                pinkLutemon = findViewById(R.id.PinkLutemon);
                orangeLutemon = findViewById(R.id.OrangeLutemon);

                blackLutemon.setVisibility(View.GONE);
                whiteLutemon.setVisibility(View.GONE);
                greenLutemon.setVisibility(View.GONE);
                pinkLutemon.setVisibility(View.GONE);
                orangeLutemon.setVisibility(View.GONE);

                if (checkedId == R.id.BlackRadioButton) {
                    blackLutemon.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.WhiteRadioButton) {
                    whiteLutemon.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.GreenRadioButton) {
                    greenLutemon.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.PinkRadioButton) {
                    pinkLutemon.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.OrangeRadioButton) {
                    orangeLutemon.setVisibility(View.VISIBLE);
                }
            }
        });
            }



    public void addLutemon(View view) {
        String name = lutemonName.getText().toString();
        int id = radioGroup.getCheckedRadioButtonId(); // id returns -1 if the user has not selected any radio buttons.
        if (name.isEmpty() || id == -1) {
            errorMessage.setText("Failed attempt, make sure you've chosen your color and named your Lutemon");
        } else if(id == R.id.BlackRadioButton) {
            Black black = new Black(name);
            LutemonStorage.getInstance().addLutemon(black);
            successfulMessage(name);
        } else if (id == R.id.WhiteRadioButton) {
            White white = new White(name);
            LutemonStorage.getInstance().addLutemon(white);
            successfulMessage(name);
        } else if (id == R.id.GreenRadioButton) {
            Green green = new Green(name);
            LutemonStorage.getInstance().addLutemon(green);
            successfulMessage(name);
        } else if (id == R.id.PinkRadioButton) {
            Pink pink = new Pink(name);
            LutemonStorage.getInstance().addLutemon(pink);
            successfulMessage(name);
        } else {
            Orange orange = new Orange(name);
            LutemonStorage.getInstance().addLutemon(orange);
            successfulMessage(name);
        }
    }

    public void successfulMessage(String name) {
        errorMessage.setText("");
        successMessage.setText("Crafting successful! " + name + " was added to your team and is ready to fight!");
    }

    public void switchBackToMainMenuActivity(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}