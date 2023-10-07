package com.hesham0_0.quizgame.models;

import java.util.List;

public class Question {
    private String question;
    private String[] answers;
    private String correctAnswer;

    public Question(String question,String[] answers,String correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer= correctAnswer;
    }
    public String[] getAnswers() {
        return answers;
    }
    public String getQuestion() {
        return question;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
