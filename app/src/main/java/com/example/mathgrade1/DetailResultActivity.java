package com.example.mathgrade1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class DetailResultActivity extends AppCompatActivity {
    private TextView tvScore;
    private Button btnRestart;
    private ImageView back, home;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailed_result);

        tvScore = findViewById(R.id.tvScore);
        btnRestart = findViewById(R.id.btnRestart);
        back = findViewById(R.id.back);
        home = findViewById(R.id.home);

        back.setOnClickListener(v -> finish());

        Bundle resultData = getIntent().getExtras();
        if (resultData != null) {
            int score = resultData.getInt("score", 0);
            int totalQuestions = resultData.getInt("total", 0);

            tvScore.setText("Your Score: " + score + "/" + totalQuestions);
        }
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailResultActivity.this, PlayActivity.class);
                startActivity(intent);
                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}