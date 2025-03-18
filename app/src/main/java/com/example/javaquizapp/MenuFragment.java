package com.example.javaquizapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        // Find buttons
        Button startButton = view.findViewById(R.id.startButton);
        Button highScoresButton = view.findViewById(R.id.highScoresButton);

        // Button to start the quiz
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Load the QuizFragment
                ((MainActivity) getActivity()).loadFragment(new QuizFragment());
            }
        });

        // Button for high scores
        highScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Load the HighScoresFragment (you need to create this)
                ((MainActivity) getActivity()).loadFragment(new HighScoresFragment());
            }
        });

        return view;
    }
}