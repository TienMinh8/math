package com.example.mathgrade1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button playButton;
    private ImageView bestscore, history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initView();
        setupListener();

    }

    private void initView() {

        playButton = findViewById(R.id.playButton);
        bestscore = findViewById(R.id.bestscore);
        history = findViewById(R.id.history);
    }

    private void setupListener() {
        playButton.setOnClickListener(v -> play());
        bestscore.setOnClickListener(v -> best());
        history.setOnClickListener(v -> historys());
    }

    private void play() {
        startActivity(new Intent(MainActivity.this, PlayActivity.class));
    }

    private void best() {
        startActivity(new Intent(MainActivity.this, BestscoreActivity.class));
    }

    private void historys() {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);
    }
}