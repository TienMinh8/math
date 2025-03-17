package com.example.mathgrade1.shareUtil;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mathgrade1.module.Answer;
import com.example.mathgrade1.module.History;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class AnswerManager {
    private static final String PREF_NAME = "MathQuizPrefs";
    private static final String KEY_ANSWERS = "user_answers";
    private static final String KEY_SCORE = "user_score";
    private static final String KEY_HISTORY = "history_answers";
    private static final String KEY_TOP_SCORE = "top_scores";
    private SharedPreferences prefs;
    private Gson gson;
    private Context mContext;
    private static final Type ANSWER_LIST_TYPE = new TypeToken<ArrayList<Answer>>() {
    }.getType();
    private static final Type HISTORY_LIST_TYPE = new TypeToken<ArrayList<History>>() {
    }.getType();

    public AnswerManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
        this.mContext = mContext;
    }

    // Lưu danh sách đáp án
    public void saveAnswers(List<Answer> answers) {
        String json = gson.toJson(answers);
        prefs.edit()
                .putString(KEY_ANSWERS, json)
                .apply();
    }

    // Đọc danh sách đáp án
    public List<Answer> getAnswers() {
        String json = prefs.getString(KEY_ANSWERS, null);
        if (json == null) {
            return new ArrayList<>();
        }
        return gson.fromJson(json, ANSWER_LIST_TYPE);
    }

    // Lưu điểm số
    public void saveScore(int score) {
        prefs.edit().putInt(KEY_SCORE, score).apply();
    }

    // Đọc điểm số
    public int getScore() {
        return prefs.getInt(KEY_SCORE, 0);
    }

    // Lưu lịch sử nhiều lượt chơi
    public void saveHistory(int score, int correctAnswers, int totalQuestion) {
        try {
            List<History> histories = getHistoryAnswer();
            if (histories == null) {
                histories = new ArrayList<>();
            }

            // Tạo lịch sử mới với điểm số và số câu trả lời đúng
            History newHistory = new History(score, correctAnswers, totalQuestion);
            histories.add(0,newHistory);

            String json = gson.toJson(histories);
            prefs.edit()
                    .putString(KEY_HISTORY, json)
                    .apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Đọc lịch sử các lượt chơi
    public List<History> getHistoryAnswer() {
        try {
            String json = prefs.getString(KEY_HISTORY, null);
            if (json == null || json.isEmpty()) {
                return new ArrayList<>();
            }
            List<History> histories = gson.fromJson(json, HISTORY_LIST_TYPE);
            return histories != null ? histories : new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Integer> getTopScores(){
        List<History> histories = getHistoryAnswer();
        List<Integer> topscore = new ArrayList<>();

        for (History result : histories){
            topscore.add(result.getCorrectAnswers());
            topscore.add(result.getScore());
        }


        Collections.sort(topscore, Collections.reverseOrder());

        if (!topscore.isEmpty()){
            return Collections.singletonList(topscore.get(0));
        }else {
            return new ArrayList<>();
        }
    }

    public void clearLastHistory() {
        List<History> historyList = getHistoryAnswer();
        if (historyList != null && !historyList.isEmpty()) {
            // Xóa lần chơi gần nhất (phần tử đầu tiên trong danh sách)
            historyList.remove(0);

            // Lưu lại danh sách đã cập nhật
            SharedPreferences.Editor editor = prefs.edit();
            String json = gson.toJson(historyList);
            editor.putString(KEY_HISTORY, json);
            editor.apply();
        }
    }

    // Xóa tất cả dữ liệu
    public void clearAll() {
        prefs.edit().clear().apply();
    }
}