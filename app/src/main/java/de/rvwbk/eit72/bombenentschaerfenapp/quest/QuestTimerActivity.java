package de.rvwbk.eit72.bombenentschaerfenapp.quest;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.rvwbk.eit72.bombenentschaerfenapp.R;

public class QuestTimerActivity extends AppCompatActivity {
    TextView countDownTimer;
    Button startButton;
    int timerTime = 30000;
    boolean success = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questtimer);

        countDownTimer = findViewById(R.id.tv_CountDownTimer_id);
        startButton = findViewById(R.id.b_StartButton_id);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(timerTime, 1000) {

                    public void onTick(long millisUntilFinished) {
                        countDownTimer.setText((int) (millisUntilFinished / 1000));
                    }
                    public void onFinish(){
                        success = false;
                    }
                }.start();
            }
        });

    }
    @Override
    protected void onStop(){
        super.onStop();
        if(success = true) {
            // implement reward here
            //
        }
    }
}
