package com.example.stonespapersscissors;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DualPlayerPlayActivity extends AppCompatActivity {

    TextView player1ScoreText,player2ScoreText,roundsTextView,turnTextView;
    Button rockButton,paperButton,scissorButton,nextRoundButton;
    ImageView player1Image,player2Image;
    String player1Name,player2Name;
    int noOfRounds;
    int player1score=0,player2score=0,currentRound=1;
    int playerState=0;
    String player1Choice="";
    String player2Choice="";
    String winner ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual_player_play);
        player1ScoreText = findViewById(R.id.player1Score);
        player2ScoreText = findViewById(R.id.player2Score);
        roundsTextView = findViewById(R.id.roundTextView);
        turnTextView = findViewById(R.id.turnTextView);
        rockButton = findViewById(R.id.rockButton);
        paperButton = findViewById(R.id.paperButton);
        scissorButton = findViewById(R.id.scissorButton);
        nextRoundButton = findViewById(R.id.nextRoundButton);
        player1Image = findViewById(R.id.player1Image);
        player2Image = findViewById(R.id.player2Image);
        Intent intent = getIntent();
        player1Name = intent.getStringExtra("player1name");
        player2Name = intent.getStringExtra("player2name");
        noOfRounds = intent.getIntExtra("noofrounds",1);
        player1ScoreText.setText(player1Name+":\n"+Integer.toString(player1score));
        player2ScoreText.setText(player2Name+":\n"+Integer.toString(player2score));
        roundsTextView.setText("Round:\n"+Integer.toString(currentRound));
        turnTextView.setText(player1Name+"'s turn");

            rockButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(playerState==0){
                        player1Image.setImageDrawable(getResources().getDrawable(R.drawable.rock));
                        turnTextView.setText(player2Name+"'s turn");
                        player1Choice="rock";
                        playerState=1;
                    }else{
                        player2Choice="rock";
                        player2Image.setImageDrawable(getResources().getDrawable(R.drawable.rock));
                        player2Click(player1Choice,player2Choice);
                    }
                }
            });
            paperButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(playerState==0){
                        player1Image.setImageDrawable(getResources().getDrawable(R.drawable.paper));
                        turnTextView.setText(player2Name+"'s turn");
                        player1Choice="paper";
                        playerState=1;
                    }else{
                        player2Choice="paper";
                        player2Image.setImageDrawable(getResources().getDrawable(R.drawable.paper));
                        player2Click(player1Choice,player2Choice);
                    }

                }
            });
            scissorButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(playerState==0){
                        player1Image.setImageDrawable(getResources().getDrawable(R.drawable.scissors));
                        turnTextView.setText(player2Name+"'s turn");
                        player1Choice="scissor";
                        playerState=1;
                    }else{
                        player2Choice="scissor";
                        player2Image.setImageDrawable(getResources().getDrawable(R.drawable.scissors));
                        player2Click(player1Choice,player2Choice);
                    }
                }
            });
            nextRoundButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(currentRound<noOfRounds) {
                        turnTextView.setVisibility(View.VISIBLE);
                        turnTextView.setText(player1Name+"'s turn");
                        player1Image.setVisibility(View.INVISIBLE);
                        player2Image.setVisibility(View.INVISIBLE);
                        rockButton.setVisibility(View.VISIBLE);
                        paperButton.setVisibility(View.VISIBLE);
                        scissorButton.setVisibility(View.VISIBLE);
                        nextRoundButton.setVisibility(View.INVISIBLE);
                        currentRound++;
                        roundsTextView.setText("Round:\n" + Integer.toString(currentRound));
                    }else{
                        if(player1score==player2score){
                            winner = "It's a Tie!!";
                        }else {
                            int winnerscore = player1score > player2score ? player1score : player2score;
                            if (winnerscore == player1score) {
                                winner = player1Name + " Wins!";
                            } else {
                                winner = player2Name + " Wins!";
                            }
                        }
                        new AlertDialog.Builder(DualPlayerPlayActivity.this)
                                .setTitle("Stones, Papers and Scissors")
                                .setMessage(winner )
                                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        System.exit(0);

                                    }
                                })
                               /* .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        System.exit(0);
                                    }
                                })*/
                                .show();
                    }
                }
            });



    }

    public void player2Click(String choice1,String choice2){
        playerState=0;
        turnTextView.setVisibility(View.INVISIBLE);
        player1Image.setVisibility(View.VISIBLE);
        player2Image.setVisibility(View.VISIBLE);
        rockButton.setVisibility(View.INVISIBLE);
        paperButton.setVisibility(View.INVISIBLE);
        scissorButton.setVisibility(View.INVISIBLE);
        nextRoundButton.setVisibility(View.VISIBLE);
        if(choice1.equals("rock")){
            if(choice2.equals("scissor")){
                player1score++;
            }else if(choice2.equals("paper")){
                player2score++;
            }
        }
        else if(choice1.equals("paper")){
            if(choice2.equals("scissor")){
                player2score++;
            }else if(choice2.equals("rock")){
                player1score++;
            }
        }
        else if(choice1.equals("scissor")){
            if(choice2.equals("rock")){
                player2score++;
            }else if(choice2.equals("paper")){
                player1score++;
            }
        }
        player1ScoreText.setText(player1Name+":\n"+Integer.toString(player1score));
        player2ScoreText.setText(player2Name+":\n"+Integer.toString(player2score));
        if(currentRound==noOfRounds){
            nextRoundButton.setText("RESULT");
        }
    }
}
