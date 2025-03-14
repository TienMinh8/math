package com.example.mathgrade1;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathgrade1.adappter.HistoryAdapter;
import com.example.mathgrade1.model.History;
import com.example.mathgrade1.shareUtil.AnswerManager;

import java.util.List;

public class BestscoreActivity extends AppCompatActivity {
    private ImageView back;
    private TextView highestScoreText, correctAnswersText;
    private RecyclerView recentScoresRecyclerView;
    private HistoryAdapter historyAdapter;
    private List<History> histories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bestscore);

        back = findViewById(R.id.back);
        highestScoreText = findViewById(R.id.highestScoreText);
        correctAnswersText = findViewById(R.id.correctAnswersText);

        AnswerManager answerManager = new AnswerManager(this);
        List<Integer> topScore = answerManager.getTopScores();

        if (topScore == null || topScore.isEmpty()){
            highestScoreText.setText("100");
            return;
        }

        highestScoreText.setText(""+ topScore.get(0));

        List<Integer> otherScoer = topScore.subList(1, topScore.size());

        recentScoresRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyAdapter = new HistoryAdapter(otherScoer);
        recentScoresRecyclerView.setAdapter(historyAdapter);
    }
}