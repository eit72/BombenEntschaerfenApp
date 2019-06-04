package de.rvwbk.eit72.bombenentschaerfenapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestActivity extends AppCompatActivity {
    List<Button> buttons = new ArrayList<Button>();
    TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = findViewById(R.id.tv_question_id);
        Button answer1 = findViewById(R.id.btn_answer_1_id);
        Button answer2 = findViewById(R.id.btn_answer_2_id);
        Button answer3 = findViewById(R.id.btn_answer_3_id);
        Button answer4 = findViewById(R.id.btn_answer_4_id);

        buttons.add(answer1);
        buttons.add(answer2);
        buttons.add(answer3);
        buttons.add(answer4);


    }

    public QuestActivity(){};

    public QuestActivity (String quest,String correctAnswer,List<String> answers){


        for(Button button: buttons){
            button.setText(quest);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
