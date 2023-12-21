package com.dadry.spygame.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.dadry.spygame.R;

public class CardBackFragment extends Fragment {

    TextView playerLabel;

//    public CardBackFragment(TextView playerLabel) {
//        this.playerLabel = playerLabel;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_card_back, container, false);
        playerLabel = (TextView) view.findViewById(R.id.cardBack);
        playerLabel.setText("SOMETEXT");
        return view;
    }

    public void setPlayerLabel(TextView textView) {
        playerLabel = textView;
    }

}
