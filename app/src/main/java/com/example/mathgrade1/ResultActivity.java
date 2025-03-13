package com.example.mathgrade1;

import android.annotation.SuppressLint;
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

public class ResultActivity extends AppCompatActivity {
    private TextView scoreText, correctAnswersText;
    private Button playAgainButton, homeButton, detailButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);

        scoreText = findViewById(R.id.scoreText);
        correctAnswersText = findViewById(R.id.correctAnswersText);
        playAgainButton = findViewById(R.id.playAgainButton);
        homeButton = findViewById(R.id.homeButton);
        detailButton = findViewById(R.id.detailButton);

        homeButton.setOnClickListener(v -> finish());

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, PlayActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Bundle resultData = getIntent().getExtras();
        if(resultData != null){
            int score = resultData.getInt("score" , 0);
            int totalQuestions = resultData.getInt("total" , 0);
            int totalScore = resultData.getInt("totalScore" , 0);

            correctAnswersText.setText("Correct Answers: " + score + "/" + totalQuestions);
            scoreText.setText("" + totalScore);
        }
        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, DetailResultActivity.class);
                intent.putExtras(resultData);
                startActivity(intent);
            }
        });
    }
}