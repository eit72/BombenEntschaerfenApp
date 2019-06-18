package de.rvwbk.eit72.bombenentschaerfenapp.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;

import de.rvwbk.eit72.bombenentschaerfenapp.MainActivity;
import de.rvwbk.eit72.bombenentschaerfenapp.R;

public class QuizActivity extends AppCompatActivity {
    private int points;

    String question = "does this work?";
    String answer = "true";
    String[] answers = {"false","false","true","false"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        System.out.println("on create quiz");
        Button startButton = findViewById(R.id.button_quiz_start);
        //Intent intent = getIntent();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent questionIntent = new Intent(QuizActivity.this, QuizQuestionActivity.class);
                questionIntent.putExtra("question", question);
                questionIntent.putExtra("answer", answer);
                questionIntent.putExtra("answers", answers);
                startActivityForResult(questionIntent, MainActivity.QUIZREQUESTCODE);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
       // System.out.println("on RESUME quiz");

        Intent resultIntent = new Intent();
        resultIntent.putExtra("points", points);
        System.out.println("points   " + points);
     //   finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("on onActivityResult requestCode " + requestCode);
        System.out.println("on onActivityResult resultCode " + (resultCode == RESULT_OK));
        if(requestCode == MainActivity.QUIZREQUESTCODE) {
            if(resultCode == RESULT_OK) {
                boolean questionSolved = data.getBooleanExtra("questionSolved", false);
                if(questionSolved) {
                    points++;
                }
                System.out.println("questionSolved: " + questionSolved);
            }
        }
    }
}
