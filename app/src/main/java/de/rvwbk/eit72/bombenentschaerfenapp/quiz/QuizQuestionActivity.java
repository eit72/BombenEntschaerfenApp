package de.rvwbk.eit72.bombenentschaerfenapp.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.rvwbk.eit72.bombenentschaerfenapp.R;

public class QuizQuestionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        List<Button> answerButtons = new ArrayList<Button>();

        Intent intent = getIntent();
        String question = intent.getStringExtra("question");
        final String answer = intent.getStringExtra("answer");
        String[] answers = intent.getStringArrayExtra("answers");

        TextView questionView = findViewById(R.id.textview_quiz_question);
        Button answerButton1 = findViewById(R.id.button_quiz_answer_1);
        Button answerButton2 = findViewById(R.id.button_quiz_answer_2);
        Button answerButton3 = findViewById(R.id.button_quiz_answer_3);
        Button answerButton4 = findViewById(R.id.button_quiz_answer_4);

        answerButtons.add(answerButton1);
        answerButtons.add(answerButton2);
        answerButtons.add(answerButton3);
        answerButtons.add(answerButton4);


        for(int i = 0; i < 4; i++) {
            Button answerButton = answerButtons.get(i);
            final String buttonText = answers[i];

            answerButton.setText(buttonText);
            answerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("questionSolved", buttonText.equals(answer));
                    finish();
                }
            });
        }
    }
}
