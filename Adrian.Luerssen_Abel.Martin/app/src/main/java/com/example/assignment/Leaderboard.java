package com.example.assignment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

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
                try {

                    JSONObject playerFormat = gson.fromJson(playerJson, JSONObject.class);

                    JSONArray playerTemp = playerFormat.getJSONArray("players");

                    for(int i = 0; i < playerTemp.length(); i++){
                        leaderboard.add(gson.fromJson((JsonElement) playerTemp.get(i), Player.class));
                    }

                } catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }

        System.out.print(playerJson);




    }
    public static String getTop10(){
        StringBuilder s = new StringBuilder();
        if (leaderboard.size() > 0){
            for (int i = 1; i < leaderboard.size(); i++) {
                s.append(i).append("). ").append(leaderboard.get(i - 1).getName()).append(leaderboard.get(i - 1).getCorrectAnswers()).append("/").append(leaderboard.get(i - 1).getQuestionsAnswered()).append("\n");
            }
        } else {
            s = new StringBuilder("no players registered");
        }

        return s.toString();
    }

    public static String getPlayers(){

        StringBuilder stringBuilder = new StringBuilder();

        for(Player player : leaderboard){
            stringBuilder.append("Name: " + player.getName() + " Score: " + player.getCorrectAnswers() + "\n");
        }

        return stringBuilder.toString();

    }

    public static String savePlayers()  {

        JSONObject tempPlayers = new JSONObject();

        try {
            tempPlayers.put("players", leaderboard);
            return gson.toJson(tempPlayers);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }


}
