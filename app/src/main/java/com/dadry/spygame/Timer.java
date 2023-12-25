package com.dadry.spygame;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.dadry.spygame.roles.Role;

import java.util.ArrayList;

public class Timer extends AppCompatActivity {

    private TextView countdownText, spyText;
    private Button startStopButton, spyButton, playAgainButton;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 180000;
    private boolean isTimerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        countdownText = findViewById(R.id.timerText);
        startStopButton = findViewById(R.id.startStopButton);
        spyButton = findViewById(R.id.spyButton);
        spyText = findViewById(R.id.spyText);
        playAgainButton = findViewById(R.id.playAgainBtn);

        playAgainButton.setClickable(false);
        playAgainButton.setVisibility(View.GONE);

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });

        spyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSpies();
                disableButtons();

                playAgainButton.setClickable(true);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        });

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
            }
        });

        updateTimer();
    }

    private void showSpies() {
        int countOfSpies = 0;
        String resultSpyText = "Шпигуни: ";

        for (int i = 0; i < PlayersCards.roles.size(); i++) {
            Role role = PlayersCards.roles.get(i);

            if (role.equals(Role.SPY)) {
                resultSpyText += i + 1;
                countOfSpies++;

                if (countOfSpies < MainActivity.finalCountOfSpies) {
                    resultSpyText += ", ";
                }
            }
        }

        spyText.setText(resultSpyText);
    }

    private void restart() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void disableButtons() {
        spyButton.setBackgroundColor(Color.parseColor("#262626"));
        startStopButton.setBackgroundColor(Color.parseColor("#262626"));

        spyButton.setClickable(false);
        startStopButton.setClickable(false);
    }

    public void startStop() {
        if (isTimerRunning) {
            spyButton.setClickable(true);
            spyButton.setBackgroundColor(Color.parseColor("#8c8c8c"));

            stopTimer();
        } else {
            spyButton.setClickable(false);
            spyButton.setBackgroundColor(Color.parseColor("#262626"));

            startTimer();
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        startStopButton.setText("Стоп");
        isTimerRunning = true;
    }

    private void stopTimer() {
        countDownTimer.cancel();
        isTimerRunning = false;
        startStopButton.setText("Старт");
    }

    private void updateTimer() {
        int minutes = (int) timeLeftInMillis / 60000;
        int seconds = (int) timeLeftInMillis % 60000 / 1000;

        String timeLeftText = minutes + ":";

        if (seconds < 10) {
            timeLeftText += "0";
        }
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);
    }
}