package de.rvwbk.eit72.bombenentschaerfenapp.quest;

import android.view.View;

public class QuestButtonListener implements View.OnClickListener {
    private boolean isAnswer;

    @Override
    public void onClick(View view) {

    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean isAnswer) {
        this.isAnswer = isAnswer;
    }
}
