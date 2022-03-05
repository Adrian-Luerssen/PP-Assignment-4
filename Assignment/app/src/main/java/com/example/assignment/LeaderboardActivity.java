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
    private SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        initVars();

        sharedPref = this.getSharedPreferences(getString(R.string.leaderboard_preferences_key),Context.MODE_PRIVATE);

        HashMap mapSharedPref = (HashMap) sharedPref.getAll();

        String leaderboard = (String) mapSharedPref.get(getString(R.string.leaderboard_preferences_key));
        leaderboardText.setText(leaderboard);
        Leaderboard.addPlayers(leaderboard);

        startGame.setOnClickListener(view -> {
            Intent newGame = new Intent(LeaderboardActivity.this, NameActivity.class);
            startActivity(newGame);
            leaderboardText.setText(Leaderboard.getPlayers());
        });
    }

    private void initVars(){
        leaderboardText = (TextView) findViewById(R.id.leaderboard);
        startGame = (Button) findViewById(R.id.start);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sharedPref.edit().putString(getString(R.string.leaderboard_preferences_key),Leaderboard.getPlayers()).apply();
        //sharedPref.edit().putString(getString(R.string.leaderboard_preferences_key),"").apply();
    }
}