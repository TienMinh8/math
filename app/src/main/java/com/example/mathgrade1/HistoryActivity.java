package com.example.mathgrade1;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathgrade1.adapter.HistoryAdapter;
import com.example.mathgrade1.module.History;
import com.example.mathgrade1.shareUtil.AnswerManager;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private ImageView back;
    private RecyclerView historyRecyclerView;
    private HistoryAdapter historyAdapter;
    private List<History> histories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);

        back = findViewById(R.id.back);
        historyRecyclerView = findViewById(R.id.historyRecyclerView);

        back.setOnClickListener(v -> finish());
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        AnswerManager answerManager = new AnswerManager(this);
        histories = answerManager.getHistoryAnswer();

        historyAdapter = new HistoryAdapter(histories);
        historyRecyclerView.setAdapter(historyAdapter);
        Log.d("History Activity", "Lịch sử lưu trữ: "+ histories.size());
    }
}