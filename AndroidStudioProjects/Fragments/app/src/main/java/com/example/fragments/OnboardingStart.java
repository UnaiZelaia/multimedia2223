package com.example.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class OnboardingStart extends Fragment {

    Button next;
    Button skip;
    NavController nav;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_onboarding_start, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nav = Navigation.findNavController(view);

        next = view.findViewById(R.id.button);
        skip = view.findViewById(R.id.button2);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nav.navigate(R.id.action_onboardingStart_to_onboarding2);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_onboardingStart_to_onboarding3);
            }
        });
    }
}