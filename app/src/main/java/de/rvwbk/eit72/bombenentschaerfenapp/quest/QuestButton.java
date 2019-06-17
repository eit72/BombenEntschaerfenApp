package de.rvwbk.eit72.bombenentschaerfenapp.quest;

import android.content.Context;

public class QuestButton extends android.widget.Button {
    private boolean isAnswer;

    public QuestButton(Context context) {
        super(context);
    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean isAnswer) {
        this.isAnswer = isAnswer;
    }
}
