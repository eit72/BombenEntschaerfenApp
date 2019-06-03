package de.rvwbk.eit72.bombenentschaerfenapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class QuestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvQuestion;
        Button btnAnswer1;
        Button btnAnswer2;
        Button btnAnswer3;
        Button btnAnswer4;

        tvQuestion = findViewById(R.id.tv_question_id);

        btnAnswer1 = findViewById(R.id.btn_answer_1_id);
        btnAnswer2 = findViewById(R.id.btn_answer_2_id);
        btnAnswer3 = findViewById(R.id.btn_answer_3_id);
        btnAnswer4 = findViewById(R.id.btn_answer_4_id);


    }

    public QuestActivity(){};

    public QuestActivity (String quest,String correctAnswer,List<String> answers){
        List<Button> ansButtons =  null;

        for(Button b: ansButtons){
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                };
            });
        }
    }
}
