package com.dadry.spygame;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static int finalCountOfPlayers;
    public static int finalCountOfSpies;

    NumberPicker countOfPlayers, countOfSpies;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countOfPlayers = findViewById(R.id.countOfPlayers);
        countOfSpies = findViewById(R.id.countOfSpies);
        startButton = findViewById(R.id.startGameButton);

        countOfSpies.setMinValue(1);
        countOfSpies.setMaxValue(4);

        countOfPlayers.setMinValue(3);
        countOfPlayers.setMaxValue(10);
    }

    public void startButtonClick(View view) {
        finalCountOfPlayers = countOfPlayers.getValue();
        finalCountOfSpies = countOfSpies.getValue();

        Intent intent = new Intent(this, PlayersCards.class);
        startActivity(intent);
    }
}