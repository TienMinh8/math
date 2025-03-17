package com.example.mathgrade1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathgrade1.adapter.HistoryAdapter;
import com.example.mathgrade1.module.History;
import com.example.mathgrade1.shareUtil.AnswerManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BestscoreActivity extends AppCompatActivity {
    private ImageView back;
    private TextView highestScoreText, correctAnswersText;
    private RecyclerView recentScoresRecyclerView;
    private HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bestscore);

        back = findViewById(R.id.back);
        highestScoreText = findViewById(R.id.highestScoreText);
        correctAnswersText = findViewById(R.id.correctAnswersText);
        recentScoresRecyclerView = findViewById(R.id.recentScoresRecyclerView);

        back.setOnClickListener(v -> finish());
        recentScoresRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        AnswerManager answerManager = new AnswerManager(this);
        List<History> historyList = answerManager.getHistoryAnswer();

        if (historyList == null || historyList.isEmpty()) {
            correctAnswersText.setText("0");
            highestScoreText.setText("Chưa có điểm");
            return;
        }

        Collections.sort(historyList, (h1, h2) -> Integer.compare(h2.getScore(), h1.getScore()));

        History bestScore = historyList.get(0);
        highestScoreText.setText(String.valueOf(bestScore.getCorrectAnswers()));
        correctAnswersText.setText("Correct: " + bestScore.getScore() + "/" + bestScore.getTotalQuestion());

        List<History> otherScores = new ArrayList<>(historyList.subList(1, historyList.size()));

        historyAdapter = new HistoryAdapter(otherScores);
        recentScoresRecyclerView.setAdapter(historyAdapter);
    }
}
