package com.dadry.spygame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.dadry.spygame.fragment.CardBackFragment;
import com.dadry.spygame.fragment.CardFrontFragment;
import com.dadry.spygame.roles.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PlayersCards extends AppCompatActivity {

    private boolean showingBack;
    public static List<Role> roles = new ArrayList<>();
    FrameLayout card;
    String word = "Слово";
    int countOfClicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_cards);

        CardBackFragment cardBackFragment = new CardBackFragment();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, cardBackFragment)
                    .commit();
        }

        for (int i = 0; i < MainActivity.finalCountOfSpies; i++) {
            roles.add(Role.SPY);
        }

        for (int i = 0; i < MainActivity.finalCountOfPlayers - MainActivity.finalCountOfSpies; i++) {
            roles.add(Role.PLAYER);
        }

        Collections.shuffle(roles);

        card = findViewById(R.id.container);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countOfClicks == roles.size()) {
                    moveToTimerActivity();
                } else {
                    flipCard();
                    onResume();
                }
            }
        });
    }

    private void moveToTimerActivity() {
        Intent intent = new Intent(this, Timer.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!showingBack) {
            TextView playerLabel = findViewById(R.id.cardBack);
            playerLabel.setText("Гравець " + (countOfClicks + 1));
        } else {
            TextView roleLabel = findViewById(R.id.roleTextView);
            roleLabel.setText("Роль: " + roles.get(countOfClicks).getLocalName());

            TextView wordLabel = findViewById(R.id.wordTextView);
            String wordForLabel;
            if (roles.get(countOfClicks).equals(Role.PLAYER)) {
                wordForLabel = "Слово: " + word;
            } else {
                wordForLabel = "Ви не знаєте слова!";
            }

            wordLabel.setText(wordForLabel);
            countOfClicks++;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView playerLabel = findViewById(R.id.cardBack);
        playerLabel.setText("Гравець 1");
    }

    private void flipCard() {
        if (showingBack) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.card_flip_right_in,
                            R.anim.card_flip_right_out,
                            R.anim.card_flip_left_in,
                            R.anim.card_flip_left_out
                    )
                    .replace(R.id.container, new CardBackFragment())
                    .addToBackStack(null)
                    .commit();

            showingBack = false;
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.card_flip_left_in,
                            R.anim.card_flip_left_out,
                            R.anim.card_flip_right_in,
                            R.anim.card_flip_right_out
                    )
                    .replace(R.id.container, new CardFrontFragment())
                    .addToBackStack(null)
                    .commit();

            showingBack = true;
        }
    }
}