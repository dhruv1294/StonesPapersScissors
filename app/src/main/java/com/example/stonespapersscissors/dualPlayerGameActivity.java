package com.example.stonespapersscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class dualPlayerGameActivity extends AppCompatActivity {
    EditText player1Name,player2Name,noOfRounds;
    Button playDualGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual_player_game);
        player1Name = findViewById(R.id.editText1);
        player2Name = findViewById(R.id.editText2);
        noOfRounds = findViewById(R.id.editText3);
        playDualGameButton = findViewById(R.id.playDualButton);
        playDualGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player1Name.getText().toString().equals("")){
                    Toast.makeText(dualPlayerGameActivity.this, "Enter player 1 name", Toast.LENGTH_SHORT).show();
                }else if(player2Name.getText().toString().equals("")){
                    Toast.makeText(dualPlayerGameActivity.this, "Enter player 2 name", Toast.LENGTH_SHORT).show();
                }else if(noOfRounds.getText().toString().equals("")){
                    Toast.makeText(dualPlayerGameActivity.this, "Enter no. of rounds", Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(noOfRounds.getText().toString())>10){
                    Toast.makeText(dualPlayerGameActivity.this, "Enter rounds less than equal to 10 ", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getApplicationContext(), DualPlayerPlayActivity.class);
                    intent.putExtra("player1name", player1Name.getText().toString());
                    intent.putExtra("player2name", player2Name.getText().toString());
                    intent.putExtra("noofrounds", Integer.parseInt(noOfRounds.getText().toString()));
                    startActivity(intent);
                }
            }
        });

    }
}
