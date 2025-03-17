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

import com.example.mathgrade1.module.Answer;
import com.example.mathgrade1.module.History;
import com.example.mathgrade1.shareUtil.AnswerManager;
import com.example.mathgrade1.shareUtil.MyApplication;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    private static final String TAG = "PlayActivity";
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private RadioGroup radioGroup;
    private TextView questionText;
    private RadioButton buttonA, buttonB, buttonC, buttonD;
    private Button nextButton;
    private int correctAnswers = 0;
    private int score = 0;
    private ImageView back;
    private AnswerManager answerManager;
    private List<Answer> answers;
    private List<History> historyList;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play);

        // lấy AnswerManager từ Myapplication để dùng chung toàn bộ app
        answerManager = MyApplication.getInstance().getAnswerManager();

        //Tạo danh sách rỗng để lưu câu trả lời của người chơi
        answers = new ArrayList<>();

        //Tạo danh sách rỗng để lưu lịch sử các lần chơi trước
        historyList = new ArrayList<>();

        initItem();

        questions = loadQuestionFromJson();
        //hiển thị câu hỏi dựa vào vị trí currentQuestionIndex ( vị trí câu hỏi hiện tại )
        displayQuestion(currentQuestionIndex);

        nextButton.setOnClickListener(v -> nextQuestion());
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

    private List<Question> loadQuestionFromJson() {
        List<Question> allQuestions = new ArrayList<>();
        List<Question> selectedQuestions = new ArrayList<>();
        try {
            InputStream is = getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");
            Log.d(TAG, "Loaded JSON: " + json);

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Question question = new Question(
                        object.getString("question"),
                        object.getString("A"),
                        object.getString("B"),
                        object.getString("C"),
                        object.getString("D"),
                        object.getString("result")
                );
                allQuestions.add(question);
            }

            // Chọn ngẫu nhiên 10 câu hỏi
            Random random = new Random();
            int maxQuestions = Math.min(10, allQuestions.size());
            
            // Tạo danh sách các chỉ số ngẫu nhiên
            List<Integer> randomIndices = new ArrayList<>();
            for (int i = 0; i < allQuestions.size(); i++) {
                randomIndices.add(i);
            }
            Collections.shuffle(randomIndices);

            // Lấy 10 câu hỏi đầu tiên sau khi đã xáo trộn
            for (int i = 0; i < maxQuestions; i++) {
                selectedQuestions.add(allQuestions.get(randomIndices.get(i)));
            }

        } catch (IOException | JSONException e) {
            Log.e(TAG, "Error loading questions: " + e.getMessage());
            e.printStackTrace();
        }
        return selectedQuestions;
    }

    private void saveAnswer(int questionNumber, String question, String userAnswer, String correctAnswer, boolean isCorrect) {
        Answer answer = new Answer(questionNumber, question, userAnswer, correctAnswer, isCorrect);
        answers.add(answer);
        answerManager.saveAnswers(answers);
        Log.d(TAG, "Saved answer: " + answer.getQuestion() + " = " + answer.getUserAnswer() + " - " + answer.isCorrect());
    }


    private void nextQuestion() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select answer!", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedAnswer = findViewById(radioGroup.getCheckedRadioButtonId());
        String answerText = selectedAnswer.getText().toString().substring(3);
        Question currentQuestion = questions.get(currentQuestionIndex);
        String correctAnswer = currentQuestion.getCorrectAnswer();
        boolean isCorrect = answerText.equals(correctAnswer);

        // lưu đáp án
        saveAnswer(currentQuestionIndex + 1, currentQuestion.getQuestion(), answerText, correctAnswer, isCorrect);

        if (isCorrect) {
            correctAnswers++;
            score += 10;
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            displayQuestion(currentQuestionIndex);
        } else {
            Toast.makeText(this, "You have completed all questions!", Toast.LENGTH_LONG).show();
            nextButton.setEnabled(false);

            Bundle resultData = new Bundle();
            resultData.putInt("score", correctAnswers);
            resultData.putInt("total", questions.size());
            resultData.putInt("totalScore", score);

            AnswerManager answerManagers = new AnswerManager(this);
            answerManagers.saveHistory(correctAnswers, score, questions.size());

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtras(resultData);
            startActivity(intent);
            finish();
        }
    }

    private void displayQuestion(int index) {
        if (index < questions.size()) {
            Question currentQuestion = questions.get(index);
            questionText.setText(currentQuestion.getQuestion());
            buttonA.setText("A. " + currentQuestion.getA());
            buttonB.setText("B. " + currentQuestion.getB());
            buttonC.setText("C. " + currentQuestion.getC());
            buttonD.setText("D. " + currentQuestion.getD());
            radioGroup.clearCheck();

            if (index == questions.size() - 1) {
                nextButton.setText("Submit");
            } else {
                nextButton.setText("Next");
            }
        }
    }
}