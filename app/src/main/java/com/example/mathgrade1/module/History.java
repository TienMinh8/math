package com.example.mathgrade1.module;

public class History {
    private int score;
    private int correctAnswers;
    private int totalQuestion;

    public History(int score, int correctAnswers, int totalQuestion) {
        this.score = score;
        this.correctAnswers = correctAnswers;
        this.totalQuestion = totalQuestion;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }
}
