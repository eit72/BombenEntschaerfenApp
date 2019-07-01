package de.rvwbk.eit72.bombenentschaerfenapp.quiz;

import java.util.ArrayList;

public class Question {
    private String question;
    private String answer;
    private String[] answers;

    public Question(String question, String answer, String[] answers){
        this.question = question;
        this.answer = answer;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String[] getAnswers() {
        return answers;
    }

    public boolean isCorrectAnswer(String answer) {
        return answer != null && this.answer.equals(answer);
    }

    public static ArrayList<Question> getDefaultQuestionList(){
        ArrayList<Question> defaultQuestionList = new ArrayList<Question>();
        for(int i = 1; i < 11; i++) {
            defaultQuestionList.add(new Question("Question " + i, "true", new String[]{"false"+ i,"false"+ i,"true"+ i,"false"+ i}));
        }
        return defaultQuestionList;
    }
}
