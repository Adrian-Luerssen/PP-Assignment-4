package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class LeaderboardActivity extends AppCompatActivity {

    private TextView leaderboardText;
    private Button startGame;
    private static SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        initVars();

        sharedPref = this.getSharedPreferences(getString(R.string.leaderboard_preferences_key),Context.MODE_PRIVATE);

        HashMap mapSharedPref = (HashMap) sharedPref.getAll();

        String leaderboard = (String) mapSharedPref.get(getString(R.string.leaderboard_preferences_key));



        Leaderboard.loadPlayers(leaderboard);
        leaderboardText.setText(Leaderboard.getTop10());

        startGame.setOnClickListener(view -> {
            Intent newGame = new Intent(LeaderboardActivity.this, NameActivity.class);
            startActivity(newGame);
            leaderboardText.setText(Leaderboard.getTop10());
        });
    }

    private void initVars(){
        leaderboardText = (TextView) findViewById(R.id.leaderboard);
        startGame = (Button) findViewById(R.id.start);
    }
    public static void writeShared(){
        sharedPref.edit().putString("LEADERBOARD_KEY",Leaderboard.savePlayers()).apply();

    }


}