package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class EndActivity extends AppCompatActivity {

    private TextView player1Result;
    private TextView player2Result;
    private TextView winnerBanner;
    private Button restart;


    private void initVars(){
        player1Result = (TextView) findViewById(R.id.player1Banner);
        player2Result = (TextView) findViewById(R.id.player2Banner);
        winnerBanner = (TextView) findViewById(R.id.WinnerBanner);
        restart = (Button) findViewById(R.id.Restart_Button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        initVars();

        Intent intent = getIntent();
        Bundle players = intent.getExtras();
        Player p1 = (Player) players.get(getString(R.string.loser));
        Player p2 = (Player) players.get(getString(R.string.winner));
        player1Result.setText(String.format(Locale.ENGLISH,getString(R.string.PlayerScoreBanner),p1.getFinalName(),p1.getCorrectAnswers()+"/"+ p1.getQuestionsAnswered()));
        player2Result.setText(String.format(Locale.ENGLISH,getString(R.string.PlayerScoreBanner),p2.getFinalName(),p2.getCorrectAnswers()+"/"+ p2.getQuestionsAnswered()));

        if (p1.getCorrectAnswers()== p2.getCorrectAnswers()) winnerBanner.setText(R.string.tie);
        else winnerBanner.setText(String.format(Locale.ENGLISH,getString(R.string.WinnerBanner),(p1.getCorrectAnswers()> p2.getCorrectAnswers())?p1.getFinalName():p2.getFinalName()));

        if (!p1.isCheater()) Leaderboard.addPlayer(p1);
        if (!p2.isCheater()) Leaderboard.addPlayer(p2);

        restart.setOnClickListener(view -> {
            finish();
        });
    }
}