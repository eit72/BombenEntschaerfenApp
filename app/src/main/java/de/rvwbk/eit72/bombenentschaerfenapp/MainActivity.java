package de.rvwbk.eit72.bombenentschaerfenapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

import de.rvwbk.eit72.bombenentschaerfenapp.quiz.QuizActivity;

public class MainActivity extends AppCompatActivity {
    public static final int QUIZREQUESTCODE = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("on create main");
        // start quiz
        Intent quizIntent = new Intent(MainActivity.this, QuizActivity.class);
        startActivityForResult(quizIntent, QUIZREQUESTCODE);
        //startActivity(quizIntent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == QUIZREQUESTCODE) {
            if(resultCode == RESULT_OK) {
                int points = data.getIntExtra("points", 0);

                System.out.println("points: " + points);
            }
        }
    }

}
