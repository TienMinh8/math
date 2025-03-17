package com.example.mathgrade1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathgrade1.adapter.AnswerAdapter;
import com.example.mathgrade1.module.Answer;
import com.example.mathgrade1.shareUtil.AnswerManager;
import com.example.mathgrade1.shareUtil.MyApplication;

import java.util.List;

public class DetailedResultActivity extends AppCompatActivity {
    private static final String TAG = "DetailedResultActivity";
    private TextView tvScore;
    private Button btnRestart;
    private ImageView back, home;
    private RecyclerView recyclerView;
    private AnswerAdapter adapter;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailed_result);

        // Initialize views
        tvScore = findViewById(R.id.tvScore);
        btnRestart = findViewById(R.id.btnRestart);
        back = findViewById(R.id.back);
        home = findViewById(R.id.home);
        recyclerView = findViewById(R.id.answersRecyclerView);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        // Get answers from AnswerManager
        AnswerManager answerManager = MyApplication.getInstance().getAnswerManager();
        List<Answer> answers = answerManager.getAnswers();
        Log.d(TAG, "Number of answers: " + answers.size());
        
        // Create and set adapter
        adapter = new AnswerAdapter(answers);
        recyclerView.setAdapter(adapter);

        back.setOnClickListener(v -> finish());

        Bundle resultData = getIntent().getExtras();
        if (resultData != null) {
            int correctAnswers = resultData.getInt("score", 0);
            int totalQuestions = resultData.getInt("total", 0);

            tvScore.setText("Your Score: " + correctAnswers + "/" + totalQuestions);
        }

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear all data
                answerManager.clearLastHistory();
                Intent intent = new Intent(DetailedResultActivity.this, PlayActivity.class);
                startActivity(intent);
                finish();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailedResultActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the adapter data
        if (adapter != null) {
            AnswerManager answerManager = MyApplication.getInstance().getAnswerManager();
            List<Answer> answers = answerManager.getAnswers();
            adapter.updateAnswers(answers);
        }
    }
} 