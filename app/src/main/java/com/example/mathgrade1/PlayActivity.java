package com.example.mathgrade1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayActivity extends AppCompatActivity {
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private RadioGroup radioGroup;
    private TextView questionText;
    private RadioButton buttonA, buttonB, buttonC, buttonD;
    private Button nextButton;
    private int score = 0;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play);

        initItem();

        questions = loadQuestionFromJson();
        nextButton.setOnClickListener(v -> nextQuestion());
        displayQuestion(currentQuestionIndex);
        back.setOnClickListener(v -> finish());

    }

    private void initItem() {
        questionText = findViewById(R.id.questionText);
        radioGroup = findViewById(R.id.radioGroup);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);
        nextButton = findViewById(R.id.nextButton);
        back = findViewById(R.id.back);
    }

    // đọc dữ liệu từ file json
    private List<Question> loadQuestionFromJson() {
        List<Question> questionList = new ArrayList<>();
        try {
            // mở file question.json trong asstes
            InputStream is = getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // chuyển đổi mảng byte(buffer) thành chuỗi JSON
        String json = new String(buffer, "UTF-8");

            Log.d("JSON_DATA",json);

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i ++){
                JSONObject object = jsonArray.getJSONObject(i);
                Question question = new Question(
                        object.getString("question"),
                        object.getString("A"),
                        object.getString("B"),
                        object.getString("C"),
                        object.getString("D"),
                        object.getString("result")
                );
                questionList.add(question);
            }
        } catch (IOException | JSONException e) {
            Log.e("JSON_ERROR", "Error loading question: " + e.getMessage());
            e.printStackTrace();
        }
        return questionList;
    }


    private void nextQuestion() {
        if (radioGroup.getCheckedRadioButtonId() == -1){
            Toast.makeText(this,"Please select answer!", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedAnswer = findViewById(radioGroup.getCheckedRadioButtonId());
        String answerText = selectedAnswer.getText().toString().substring(3);

        if (answerText.equals(questions.get(currentQuestionIndex).getCorrectAnswer())){
            score++;
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()){
            displayQuestion(currentQuestionIndex);
        }else {
            Toast.makeText(this, "You have activated all the answers!", Toast.LENGTH_LONG).show();
            nextButton.setEnabled(false);

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("total", questions.size());
            startActivity(intent);
            finish();
        }
    }

    private void displayQuestion(int index) {
        if (index < questions.size()){
            Question currentQuestion = questions.get(index);
            questionText.setText(currentQuestion.getQuestion());
            buttonA.setText("A. "+ currentQuestion.getA());
            buttonB.setText("B. "+ currentQuestion.getB());
            buttonC.setText("C. "+ currentQuestion.getC());
            buttonD.setText("D. "+ currentQuestion.getD());
            radioGroup.clearCheck();

            if (index == questions.size() - 1){
                nextButton.setText("Submit");
            }else {
                nextButton.setText("Next");
            }

        }
    }

}