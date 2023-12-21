package com.dadry.spygame.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.dadry.spygame.R;

public class CardFrontFragment extends Fragment {

    TextView roleLabel, wordLabel;

//    public CardFrontFragment(TextView roleLabel, TextView wordLabel) {
//        this.roleLabel = roleLabel;
//        this.wordLabel = wordLabel;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_card_front, container, false);

        roleLabel = (TextView) view.findViewById(R.id.roleTextView);
        wordLabel = (TextView) view.findViewById(R.id.wordTextView);

        roleLabel.setText("SOMEROLE");
        wordLabel.setText("SOMEWORD");

        return view;
    }

    public void setRoleLabel(TextView textView) {
        roleLabel = textView;
    }

    public void setWordLabel(TextView textView) {
        wordLabel = textView;
    }
}