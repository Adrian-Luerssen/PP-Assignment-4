package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CHEAT_CODE = 999;

    private TextView question;
    private TextView turnText;

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button backButton;

    private Player player1;
    private Player player2;
    private Button cheater;
    
    private int turn = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();

        QuestionPool questionPool = new QuestionPool();
        // saving the question and answer strings for later use
        // done as variable to make it more readable
        String[] questionArray = getResources().getStringArray(R.array.questions);
        String[] answerArray = getResources().getStringArray(R.array.answers);
        questionPool.init();
        initVars();

        if(extras != null){
            player1 = (Player) extras.get("player1");
            player2 = (Player) extras.get("player2");
        }



        setPlayersTurn();

        // filling the question pool with the previously loaded strings
        // boolean parsing is required in the answerArray, done with equals
        for(int i = 0; !questionArray[i].equals("No question has been loaded"); i++){
            questionPool.addQuestion(questionArray[i], answerArray[i].equals("TRUE"));
        }

        question.setText(questionPool.getQuestionString());

        // setting the initial question on start up
        // creating the toast for a correct answer
        Toast correctToast = Toast.makeText(getApplicationContext(), R.string.toast_correct, Toast.LENGTH_LONG);
        correctToast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 250);

        // creating the toast for an incorrect answer
        Toast incorrectToast = Toast.makeText(getApplicationContext(), R.string.toast_incorrect, Toast.LENGTH_LONG);
        incorrectToast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 250);

        // creating the toast for the question already answered
        Toast alreadyAnsweredToast = Toast.makeText(getApplicationContext(), R.string.toast_answered, Toast.LENGTH_LONG);
        alreadyAnsweredToast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 250);


        trueButton.setOnClickListener(view -> {
            // changes the display question
            System.out.print(questionPool.isCurrentQuestionAnswered(turn));
            if (questionPool.isCurrentQuestionAnswered(turn)){
                // the user presses true, display the corresponding toast if the answer is correct or not
                if (questionPool.answerIsCorrect(true)) {

                    correctToast.setText(getString(R.string.toast_correct)+ "\n" + questionPool.getQuestionExplanation());
                    correctToast.show();
                    questionPool.answeredCurrentQuestion(true, turn);
                    addPointToPlayer();

                } else {

                    incorrectToast.setText(getString(R.string.toast_incorrect) + "\n" + questionPool.getQuestionExplanation());
                    incorrectToast.show();
                    questionPool.answeredCurrentQuestion(false, turn);

                }

                addQuestionToPlayer();
                turn = (turn%2)+1;
                setPlayersTurn();


            } else {

                alreadyAnsweredToast.setText(String.format(getString(R.string.toast_answered), getTurnPlayerName()));
                alreadyAnsweredToast.show();

            }

            if(isQuestionsAnswered(questionPool.getTotalQuestions())){
                endGame();
            }

        });

        falseButton.setOnClickListener(view -> {
            // changes the display question
            System.out.print(questionPool.isCurrentQuestionAnswered(turn));

            if (questionPool.isCurrentQuestionAnswered(turn)){

                // the user presses true, display the corresponding toast if the answer is correct or not
                if (questionPool.answerIsCorrect(false)) {
                    correctToast.setText(getString(R.string.toast_correct) + "\n" + questionPool.getQuestionExplanation());
                    correctToast.show();
                    questionPool.answeredCurrentQuestion(true, turn);
                    addPointToPlayer();
                } else {
                    incorrectToast.setText(getString(R.string.toast_incorrect)+"\n"+questionPool.getQuestionExplanation());
                    incorrectToast.show();
                    questionPool.answeredCurrentQuestion(false, turn);
                }

                addQuestionToPlayer();
                turn = (turn%2)+1;
                setPlayersTurn();

            } else {
                alreadyAnsweredToast.setText(String.format(getString(R.string.toast_answered), getTurnPlayerName()));
                alreadyAnsweredToast.show();
            }

            if(isQuestionsAnswered(questionPool.getTotalQuestions())){
                endGame();
            }



        });



        nextButton.setOnClickListener(view -> {
            questionPool.nextQuestion();
            question.setText(questionPool.getQuestionString()); // changes the display question
        });

        backButton.setOnClickListener(view -> {
            questionPool.backQuestion();
            question.setText(questionPool.getQuestionString()); // changes the display question

        });

        cheater.setOnClickListener(view -> {
            Intent myIntent = CheatActivity.newIntent(MainActivity.this,questionPool.answerIsCorrect(true));
            startActivityForResult(myIntent,REQUEST_CHEAT_CODE);

        });

    }

    private void setPlayersTurn() {
        if (turn == 1) turnText.setText(String.format(getString(R.string.playersTurn),player1.getName()));
        if (turn == 2) turnText.setText(String.format(getString(R.string.playersTurn),player2.getName()));
    }

    private void addQuestionToPlayer() {
        if (turn == 1) player1.incrementAnswered();
        if (turn == 2) player2.incrementAnswered();
    }

    private String getTurnPlayerName(){

        if (turn == 1) return player1.getName();
        if (turn == 2) return player2.getName();

        return null;
    }

    private void addPointToPlayer() {
        if (turn == 1) player1.incrementCorrect();
        if (turn == 2) player2.incrementCorrect();
    }

    public void initVars(){
        cheater = (Button) findViewById(R.id.BecomeCheaterButton);
        backButton = (Button) findViewById(R.id.back_button);
        nextButton = (Button) findViewById(R.id.next_button);
        trueButton = (Button) findViewById(R.id.true_button);   // true button in the view
        falseButton = (Button) findViewById(R.id.false_button); // false button in the view
        question = (TextView) findViewById(R.id.question); // text box displaying the question in the view
        turnText = (TextView) findViewById(R.id.turnText);
    }

    public void endGame(){
        // passing all of the game information to the ending screen
        Intent myIntent = new Intent(MainActivity.this, EndActivity.class);
        myIntent.putExtra(getString(R.string.winnerNameExtra), (player2.getCorrectAnswers() > player1.getCorrectAnswers())?player2.getFinalName():player1.getFinalName()); //Optional parameters
        myIntent.putExtra(getString(R.string.winnerScoreExtra), (player2.getCorrectAnswers() > player1.getCorrectAnswers())?player2.getCorrectAnswers()+"/"+ player2.getQuestionsAnswered():player1.getCorrectAnswers()+"/"+ player1.getQuestionsAnswered()); //Optional parameters
        myIntent.putExtra(getString(R.string.loserNameExtra), (player2.getCorrectAnswers() < player1.getCorrectAnswers())?player2.getFinalName():player1.getFinalName()); //Optional parameters
        myIntent.putExtra(getString(R.string.loserScoreExtra), (player2.getCorrectAnswers() < player1.getCorrectAnswers())?player2.getCorrectAnswers()+"/"+ player2.getQuestionsAnswered():player1.getCorrectAnswers()+"/"+ player1.getQuestionsAnswered()); //Optional parameters
        MainActivity.this.startActivity(myIntent);
    }


    private boolean isQuestionsAnswered(int totalQuestions){

        return player1.getQuestionsAnswered() == player2.getQuestionsAnswered()
                && player1.getQuestionsAnswered() == totalQuestions;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CHEAT_CODE) { // true if the player has pressed the button to reveal the answer
            System.out.println("HE CHEATIN'");
            if (data == null) {
                return;
            }
            if (turn == 1) {
                player1.setCheater(true);
            } else if (turn == 2) {
                player2.setCheater(true);
            }
        }
    }

}