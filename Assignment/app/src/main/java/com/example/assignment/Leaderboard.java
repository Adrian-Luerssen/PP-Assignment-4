package com.example.assignment;


import java.util.ArrayList;

public class Leaderboard {
    private static ArrayList<String> leaderboard = new ArrayList<>();


    public static void addPlayer (String playerName, String score){
        leaderboard.add(findPos(playerName+": "+score),playerName+": "+score);

    }

    private static int findPos(String newPlayer) {
        int i =0;
        for (String player : leaderboard){
            if (ratio(player) < ratio(newPlayer)) return i;
            i++;
        }
        return i;
    }

    private static float ratio(String s){
        float a = Float.parseFloat(s.split(":")[1].split("/")[0]);
        float b = Float.parseFloat(s.split(":")[1].split("/")[1]);
        return a/b;
    }

    public static void addPlayers(String leaderboard) {
        for (String player: leaderboard.split("\n")){
            if (player.split(":").length == 2 && player.split(":")[1].split("/").length == 2) addPlayer(player);
        }
    }

    private static void addPlayer(String player) {
        leaderboard.add(findPos(player),player);
    }

    public static String getPlayers() {
        String concatPlayers = "";
        for (int i = 0; i<leaderboard.size()-1;i++){
            concatPlayers+= leaderboard.get(i)+"\n";
        }
        concatPlayers+= leaderboard.get(leaderboard.size()-1);
        return concatPlayers;
    }

    public static void addPlayer(String name, int correctAnswers, int questionsAnswered) {
        leaderboard.add(findPos(name+": "+correctAnswers+"/"+questionsAnswered),name+": "+correctAnswers+"/"+questionsAnswered);
    }
}
