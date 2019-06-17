package de.rvwbk.eit72.bombenentschaerfenapp.quiz;

import android.view.View;
import android.content.Intent;

import de.rvwbk.eit72.bombenentschaerfenapp.R;

public class QuizButtonListener implements View.OnClickListener {
    private boolean isAnswer;

    @Override
    public void onClick(View view) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("questionSolved", isAnswer);


    }

    public void setAnswer(boolean isAnswer) {
        this.isAnswer = isAnswer;
    }
}
