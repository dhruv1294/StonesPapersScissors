package com.example.stonespapersscissors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button dualPlayerButton;
    Button singlePlayerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dualPlayerButton = findViewById(R.id.dualPlayerButton);
        singlePlayerButton = findViewById(R.id.singlePlayerbutton);
        dualPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),dualPlayerGameActivity.class);
                startActivity(intent);
            }
        });
    }
}
