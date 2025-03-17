package com.example.mathgrade1.module;

public class Answer {
    private int questionNumber;
    private String question;
    private String userAnswer;
    private String correctAnswer;
    private boolean isCorrect;

    public Answer(int questionNumber, String question, String userAnswer, String correctAnswer, boolean isCorrect ) {
        this.questionNumber = questionNumber;
        this.question = question;
        this.userAnswer = userAnswer;
        this.correctAnswer = correctAnswer;
        this.isCorrect = isCorrect;
    }

    // Getters
    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
    // Setters
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
} 