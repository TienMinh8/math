package com.example.mathgrade1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathgrade1.R;
import com.example.mathgrade1.module.History;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<History> historyList;

    public HistoryAdapter(List<History> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        History history = historyList.get(position);
        holder.totalScore.setText("Your Score: "+ history.getCorrectAnswers());
        holder.numberOfAnswers.setText("Number: " +history.getScore()+"/"+history.getTotalQuestion());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView totalScore, numberOfAnswers;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            totalScore = itemView.findViewById(R.id.totalScore);
            numberOfAnswers = itemView.findViewById(R.id.numberOfAnswers);
        }
    }
}
