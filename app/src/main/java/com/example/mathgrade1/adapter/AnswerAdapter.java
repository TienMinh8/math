package com.example.mathgrade1.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathgrade1.R;
import com.example.mathgrade1.module.Answer;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder> {
    private static final String TAG = "AnswerAdapter";
    private List<Answer> answers;

    public AnswerAdapter(List<Answer> answers) {
        this.answers = answers;
        Log.d(TAG, "Adapter created with " + answers.size() + " answers");
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_answer, parent, false);
        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        Answer answer = answers.get(position);
        Log.d(TAG, "Binding answer at position " + position + ": " + answer.getQuestion());
        
        holder.questionNumber.setText("Question " + answer.getQuestionNumber());
        holder.questionText.setText(answer.getQuestion());
        holder.userAnswer.setText(answer.getUserAnswer());
        holder.correctAnswer.setText(answer.getCorrectAnswer());
        
        // Set icon based on answer correctness
        holder.statusIcon.setImageResource(
            answer.isCorrect() ? R.drawable.ic_correct : R.drawable.ic_wrong
        );
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public void updateAnswers(List<Answer> newAnswers) {
        Log.d(TAG, "Updating answers from " + answers.size() + " to " + newAnswers.size());
        this.answers = newAnswers;
        notifyDataSetChanged();
    }

    static class AnswerViewHolder extends RecyclerView.ViewHolder {
        TextView questionNumber;
        TextView questionText;
        TextView userAnswer;
        TextView correctAnswer;
        ImageView statusIcon;

        AnswerViewHolder(View itemView) {
            super(itemView);
            questionNumber = itemView.findViewById(R.id.questionNumber);
            questionText = itemView.findViewById(R.id.questionText);
            userAnswer = itemView.findViewById(R.id.userAnswer);
            correctAnswer = itemView.findViewById(R.id.correctAnswer);
            statusIcon = itemView.findViewById(R.id.statusIcon);
        }
    }
}