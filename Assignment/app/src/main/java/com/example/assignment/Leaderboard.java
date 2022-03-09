package com.example.assignment;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard {

    private static final ArrayList<Player> leaderboard = new ArrayList<>();

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void addPlayer(Player player) {
        leaderboard.add(player);
        Collections.sort(leaderboard);
    }

    public static void loadPlayers(String playerJson) {
        if (playerJson != null){
            if (!playerJson.equals("[]")){
                JSONArray playerFormat = gson.fromJson(playerJson, JSONArray.class);

                try {

                    if(playerJson != null) {
                        for (int i = 0; i < playerFormat.length(); i++) {
                            leaderboard.add(gson.fromJson(String.valueOf(playerFormat.getJSONObject(i)), Player.class));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.print(playerJson);




    }
    public static String getTop10(){
        String s = "";
        if (leaderboard.size() > 0){
            for (int i = 1; i < 11; i++) {
                s+=i+"). "+leaderboard.get(i-1).getName()+leaderboard.get(i-1).getCorrectAnswers()+"/"+leaderboard.get(i-1).getQuestionsAnswered()+"\n";
            }
        } else {
            s = "no players registered";
        }

        return s;
    }

    public static String getPlayers(){

        StringBuilder stringBuilder = new StringBuilder();

        for(Player player : leaderboard){
            stringBuilder.append("Name: " + player.getName() + " Score: " + player.getCorrectAnswers() + "\n");
        }

        return stringBuilder.toString();

    }

    public static String savePlayers(){

        return gson.toJson(leaderboard);

    }


}
