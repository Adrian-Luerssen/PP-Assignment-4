package com.example.assignment;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Player implements Serializable {

    private String name;
    private int correctAnswers;
    private int questionsAnswered;
    private boolean isCheater;

    private boolean[] questionState;

    public Player(String name) {
        this.name = name;
    }

    public void initializeQuestions(){

    }

    public String getFinalName() { return (isCheater)?"Cheater "+name:"Player "+name; }
    public String getName() { return name;}
    public void setName(String name) { this.name = name; }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void incrementCorrect() {
        this.correctAnswers++;
    }

    public int getQuestionsAnswered() {
        return questionsAnswered;
    }

    public void incrementAnswered() {
        this.questionsAnswered++;
    }

    public boolean isCheater() {
        return isCheater;
    }

    public void setCheater(boolean cheater) {
        isCheater = cheater;
    }





}
