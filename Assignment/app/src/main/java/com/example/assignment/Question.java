package com.example.assignment;

public class Question {
    private final String question;
    private final boolean correctAnswer;
    private boolean[] isAnswered;
    private final String explanation;



    public boolean isCorrect(boolean answer) {
        return this.correctAnswer == answer;
    }

    public Question(String question, boolean correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.explanation = "";
        this.isAnswered = new boolean[]{false, false};
    }

    // early concept to add explanation to question
    // deprecated as of now, will probably come back in the future
    @Deprecated
    public Question(String question, boolean correctAnswer, String explanation) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
    }


    // GETTERS
    public String getQuestion() {
        return question;
    }

    public String getExplanation(){return explanation;}

    public boolean isAnswered(int currentPlayer){
        return this.isAnswered[currentPlayer - 1];
    }

    // SETTERS
    public void setAnswerState(boolean state, int currentPlayer){
        this.isAnswered[currentPlayer - 1] = state;
    }

}
