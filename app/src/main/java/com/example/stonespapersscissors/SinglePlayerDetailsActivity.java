package com.example.stonespapersscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SinglePlayerDetailsActivity extends AppCompatActivity {
    EditText playerName,noOfRounds;
    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player_details);
        playerName = findViewById(R.id.editText);
        noOfRounds = findViewById(R.id.editText4);
        playButton = findViewById(R.id.button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playerName.getText().toString().equals("")){
                    Toast.makeText(SinglePlayerDetailsActivity.this, "Enter Player name", Toast.LENGTH_SHORT).show();
                }else if(noOfRounds.getText().toString().equals("")){
                    Toast.makeText(SinglePlayerDetailsActivity.this, "Enter no of rounds", Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(noOfRounds.getText().toString())>10){
                    Toast.makeText(SinglePlayerDetailsActivity.this, "Enter rounds less than 11", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(),SinglePlayerPlayActivity.class);
                    intent.putExtra("playerName",playerName.getText().toString());
                    intent.putExtra("rounds",Integer.parseInt(noOfRounds.getText().toString()));
                    startActivity(intent);
                }

            }
        });
    }
}
