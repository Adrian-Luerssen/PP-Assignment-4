package com.example.assignment;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Player implements Serializable, Comparable {

    @SerializedName("name")
    private String name;
    @SerializedName("correctAnswers")
    private int correctAnswers;
    @SerializedName("questionsAnswered")
    private int questionsAnswered;
    @SerializedName("isCheater")
    private boolean isCheater;

    public Player(String name) {
        this.name = name;
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

    public float getRatio() {
        return (float)correctAnswers / (float)questionsAnswered;
    }


    @Override
    public int compareTo(Object o) {
        return this.getCorrectAnswers() - ((Player) o).getCorrectAnswers();
    }
}
