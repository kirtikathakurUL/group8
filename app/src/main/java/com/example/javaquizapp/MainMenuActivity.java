package com.example.javaquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button startButton = findViewById(R.id.startButton);
        Button highScoresButton = findViewById(R.id.highScoresButton);

        //button to start the quiz
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        //Button for high scores (to be used when database is set up)
        highScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Add High Scores Activity
                Intent intent = new Intent(MainMenuActivity.this, HighScoresActivity.class);
                startActivity(intent);
            }
        });
    }
}