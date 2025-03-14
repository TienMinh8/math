package com.example.mathgrade1.adappter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathgrade1.R;

import java.util.List;

public class TopScoreAdapter extends RecyclerView.Adapter<TopScoreAdapter.TopScoreViwHolder> {
    private List<Integer> topscoer;

    @NonNull
    @Override
    public TopScoreViwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TopScoreViwHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class TopScoreViwHolder extends RecyclerView.ViewHolder{
        TextView totalScore;
        TextView numberOfAnswers;
        ImageView st;


        public TopScoreViwHolder(@NonNull View itemView) {
            super(itemView);
            totalScore = itemView.findViewById(R.id.totalScore);
            numberOfAnswers = itemView.findViewById(R.id.numberOfAnswers);
            st = itemView.findViewById(R.id.st);
        }
    }
}
