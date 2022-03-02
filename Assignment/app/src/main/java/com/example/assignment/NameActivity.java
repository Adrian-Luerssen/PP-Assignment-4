package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class NameActivity extends AppCompatActivity {


    private TextView menuText;

    private EditText usernameInput;

    private Button saveButton;

    private Intent intent;

    private int click_ctr;

    private void initVars(){
        menuText = (TextView) findViewById(R.id.userInputBanner);
        usernameInput = (EditText) findViewById(R.id.inputMenuText);
        saveButton = (Button) findViewById(R.id.saveButton);
        click_ctr = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        initVars();

        Toast wrongInput = Toast.makeText(getApplicationContext(), R.string.toast_wrong_input, Toast.LENGTH_LONG);
        wrongInput.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 250);

        intent = new Intent(NameActivity.this, MainActivity.class);

        saveButton.setOnClickListener(view -> {
            if(!usernameInput.getText().toString().equals("")) {

                click_ctr++;

                if(click_ctr == 2){
                    intent.putExtra("player2", new Player(usernameInput.getText().toString()));
                    startActivity(intent);
                } else {
                    menuText.setText(String.format(getString(R.string.username_input_menu), click_ctr + 1));
                    intent.putExtra("player1", new Player(usernameInput.getText().toString()));
                    usernameInput.setText("");
                }



            } else {
                wrongInput.show();
            }

            System.out.printf(Locale.US, "added: %d\n", click_ctr);

        });








        menuText.setText(String.format(getString(R.string.username_input_menu), 1));



    }


}