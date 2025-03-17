package com.example.mathgrade1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathgrade1.R;
import com.example.mathgrade1.module.History;

import java.util.List;

public class BestScoreAdapter extends RecyclerView.Adapter<BestScoreAdapter.ViewHolder> {
    private List<History> historyList;

    public BestScoreAdapter(List<History> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bestscore, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        History history = historyList.get(position);
        
        // Hiển thị điểm số
        holder.scoreText.setText("Score: " + history.getCorrectAnswers());
        
        // Hiển thị số câu trả lời đúng
        holder.correctAnswersText.setText(history.getScore() + "/" + history.getTotalQuestion() + " Correct");
        
        // Hiển thị vị trí xếp hạng
        holder.positionText.setText("#" + (position + 2)); // +2 vì điểm cao nhất đã hiển thị ở trên

        // Thay đổi icon medal dựa vào vị trí
        if (position == 0) {
            holder.medalIcon.setImageResource(R.drawable.top2); // Silver medal
        } else if (position == 1) {
            holder.medalIcon.setImageResource(R.drawable.top3); // Bronze medal
        } else {
            holder.medalIcon.setImageResource(R.drawable.study); // Normal icon
        }
    }

    @Override
    public int getItemCount() {
        return historyList != null ? historyList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView scoreText, correctAnswersText, positionText;
        ImageView medalIcon;

        ViewHolder(View itemView) {
            super(itemView);
            scoreText = itemView.findViewById(R.id.scoreText);
            correctAnswersText = itemView.findViewById(R.id.correctAnswersText);
            positionText = itemView.findViewById(R.id.positionText);
            medalIcon = itemView.findViewById(R.id.medalIcon);
        }
    }
}
