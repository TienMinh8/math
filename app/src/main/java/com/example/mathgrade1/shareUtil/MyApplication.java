package com.example.mathgrade1.shareUtil;

import android.app.Application;

public class MyApplication extends Application {
    private static MyApplication instance;
    private AnswerManager answerManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        answerManager = new AnswerManager(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public AnswerManager getAnswerManager() {
        return answerManager;
    }
} 