package com.dadry.spygame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.dadry.spygame.roles.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayersCards extends AppCompatActivity {

    private boolean showingBack;
    public static List<Role> roles = new ArrayList<>();
    FrameLayout card;
    String word = "Слово: ";

    public static class CardFrontFragment extends Fragment {

//        TextView roleLabel, wordLabel;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_front, container, false);
        }

//        public CardFrontFragment setRoleLabel(String text) {
//            roleLabel.setText(text);
//
//            return this;
//        }
//
//        public CardFrontFragment setWordLabel(String text) {
//            wordLabel.setText(text);
//
//            return this;
//        }
    }

    public static class CardBackFragment extends Fragment {

//        TextView playerLabel;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_back, container, false);
        }

//        public CardBackFragment setPlayerLabel(String text) {
//            playerLabel.setText(text);
//
//            return this;
//        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_cards);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardBackFragment())
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
                flipCard();
            }
        });
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