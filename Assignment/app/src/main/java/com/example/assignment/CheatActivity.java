package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private Button showAnswer;
    private boolean cheated;

    public static Intent newIntent(Context context, boolean answerIsTrue) {
        Intent intent = new Intent(context,CheatActivity.class);
        intent.putExtra("answer is true",answerIsTrue);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Intent intent = getIntent();
        boolean answerIsTrue = intent.getBooleanExtra("answer is true",false); //if it's a string you stored.

        showAnswer = findViewById(R.id.CheaterConfirmationButton);

        showAnswer.setOnClickListener(view -> {
            if (answerIsTrue) ((TextView)findViewById(R.id.answer)).setText(R.string.answerIsTrue);
            else ((TextView)findViewById(R.id.answer)).setText(R.string.answerIsFalse);
            setResult(MainActivity.REQUEST_CHEAT_CODE);
        });

    }


}