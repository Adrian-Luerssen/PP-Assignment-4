package com.example.assignment;

import java.util.ArrayList;

public class QuestionPool {

    private ArrayList<com.example.assignment.Question> questions;

    private int index;

    public void init(){

        index = 0;

        questions = new ArrayList<>();

    }

    //GETTERS

    public String getQuestionExplanation(){
        return questions.get(index).getExplanation();
    }

    public String getQuestionString() {
        return questions.get(index).getQuestion();
    }

    public boolean isCurrentQuestionAnswered(int currentPlayer){
        return !questions.get(index).isAnswered(currentPlayer);
    }

    public int getTotalQuestions(){
        return questions.size();
    }

    public boolean answerIsCorrect(boolean answer) {

        return questions.get(index).isCorrect(answer);

    }

    //SETTERS

    public void nextQuestion() {

        index++;

        if (index == questions.size()) {
            index = 0;
        }

        // debugging
        System.out.println(index);

    }

    public void backQuestion() {

        index--;

        if (index < 0) {
            index = questions.size() - 1;
        }

        // debugging
        System.out.println(index);

    }

    public void addQuestion(String question, boolean correctAnswer){
        questions.add(new com.example.assignment.Question(question,correctAnswer));
    }

    public void answeredCurrentQuestion(boolean answerCorrect, int currentPlayer){

        questions.get(index).setAnswerState(true, currentPlayer);

    }

    public void restartQuestions(){

        index = 0;

        for(int i = 0; i < questions.size(); i++){
            questions.get(i).setAnswerState(false, 0);
        }

        for(int i = 0; i < questions.size(); i++){
            questions.get(i).setAnswerState(false, 1);
        }

    }
}
