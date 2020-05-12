package com.example.stonespapersscissors;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class SinglePlayerPlayActivity extends AppCompatActivity {
    TextView playerScore,compScore,noOfRounds,turnTextView;
    Button rockButton,paperButton,scissorButton,nextRoundButton;
    ImageView playerImage,compImage;
    String playerName;
    ConstraintLayout constraintLayout;
    int rounds;
    int currentRound=1;
    int compChoice;
    String playerChoice;
    int playerScoreMarks=0,compScoreMarks=0;
    String winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player_play);
        playerScore = findViewById(R.id.playerScore);
        compScore = findViewById(R.id.compScore);
        noOfRounds = findViewById(R.id.noOfRoundTextView);
        turnTextView = findViewById(R.id.turnSingleTextView);
        rockButton=findViewById(R.id.rockSingleButton);
        paperButton=findViewById(R.id.paperSingleButton);
        scissorButton=findViewById(R.id.scissorSingleButton);
        nextRoundButton=findViewById(R.id.nextSingleRoundButton);
        playerImage=findViewById(R.id.playerImage);
        compImage=findViewById(R.id.compImage);
        constraintLayout = findViewById(R.id.constraintbg);
        Intent intent = getIntent();
        rounds = intent.getIntExtra("rounds",1);
        playerName = intent.getStringExtra("playerName");
        playerScore.setText(playerName+":\n" + Integer.toString(playerScoreMarks));
        compScore.setText("Comp:\n"+Integer.toString(compScoreMarks));
        noOfRounds.setText("Round:\n"+Integer.toString(currentRound));
        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerClick("rock");
            }
        });
        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerClick("paper");
            }
        });
        scissorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerClick("scissor");
            }
        });
        nextRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentRound < rounds) {

                    playerImage.setVisibility(View.INVISIBLE);
                    compImage.setVisibility(View.INVISIBLE);
                    rockButton.setVisibility(View.VISIBLE);
                    paperButton.setVisibility(View.VISIBLE);
                    scissorButton.setVisibility(View.VISIBLE);
                    nextRoundButton.setVisibility(View.INVISIBLE);
                    currentRound++;
                    noOfRounds.setText("Round:\n" + Integer.toString(currentRound));
                } else {
                    if (playerScoreMarks == compScoreMarks) {
                        winner = "It's a Tie!!";
                    } else {
                        int winnerscore = playerScoreMarks > compScoreMarks ? playerScoreMarks : compScoreMarks;
                        if (winnerscore == playerScoreMarks) {
                            winner = playerName + " Wins!";
                            playerWin();

                        } else {
                           playerLose();
                            winner = playerName + " loses! Better Luck next time";
                        }
                    }
                        new AlertDialog.Builder(SinglePlayerPlayActivity.this)
                                .setTitle("Stones, Papers and Scissors")
                                .setMessage(winner )
                                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        System.exit(0);

                                    }
                                })
                                .show();

                }

            }

        });


    }

    public void playerClick(String choice){
        turnTextView.setVisibility(View.INVISIBLE);

        rockButton.setVisibility(View.INVISIBLE);
        paperButton.setVisibility(View.INVISIBLE);
        scissorButton.setVisibility(View.INVISIBLE);
        nextRoundButton.setVisibility(View.VISIBLE);
        Random random = new Random();
        compChoice = random.nextInt(3);
        if(compChoice==0){
            compImage.setImageDrawable(getResources().getDrawable(R.drawable.rock));
        }else if(compChoice==1){
            compImage.setImageDrawable(getResources().getDrawable(R.drawable.paper));
        }else if(compChoice==2){
            compImage.setImageDrawable(getResources().getDrawable(R.drawable.scissors));
        }
        if(choice.equals("rock")){
            playerImage.setImageDrawable(getResources().getDrawable(R.drawable.rock));
            if(compChoice==1){
                compScoreMarks++;
                playerLose();;
            }else if(compChoice==2){
                playerScoreMarks++;
                playerWin();
            }
        }else if(choice.equals("paper")){
            playerImage.setImageDrawable(getResources().getDrawable(R.drawable.paper));
            if(compChoice==0){
                playerScoreMarks++;
                playerWin();
            }else if(compChoice==2){
                compScoreMarks++;
                playerLose();
            }
        }else if(choice.equals("scissor")){
            playerImage.setImageDrawable(getResources().getDrawable(R.drawable.scissors));
            if(compChoice==0){
                compScoreMarks++;
                playerLose();
            }else if(compChoice==1){
                playerScoreMarks++;
                playerWin();
            }
        }
        playerScore.setText(playerName+":\n"+Integer.toString(playerScoreMarks));
        compScore.setText("Comp:\n"+Integer.toString(compScoreMarks));
        if(currentRound==rounds){
            nextRoundButton.setText("RESULT");
        }
        playerImage.setVisibility(View.VISIBLE);
        compImage.setVisibility(View.VISIBLE);

    }

    public void playerWin(){
        int colorFrom = Color.parseColor("#00ff00");
        int colorTo = Color.parseColor("#FFAFBD");
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(2500); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                constraintLayout.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }

    public void playerLose(){
        int colorFrom = Color.RED;
        int colorTo = Color.parseColor("#FFAFBD");
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(2500); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                constraintLayout.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }
}
