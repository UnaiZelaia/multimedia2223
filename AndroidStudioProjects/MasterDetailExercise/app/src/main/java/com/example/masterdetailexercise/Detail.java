package com.example.masterdetailexercise;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.masterdetailexercise.databinding.FragmentDetailBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Detail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Detail extends Fragment {

    private FragmentDetailBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = FragmentDetailBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ChampionsViewModel championsViewModel = new ViewModelProvider(requireActivity()).get(ChampionsViewModel.class);

        championsViewModel.selected().observe(getViewLifecycleOwner(), new Observer<Champion>() {
            @Override
            public void onChanged(Champion champion) {
                binding.name.setText(champion.name);

                if (champion.winRate <= 50){
                    binding.winRate.setText("Win rate: " + champion.winRate);
                    binding.winRate.setTextColor(Color.RED);
                }
                else {
                    binding.winRate.setText("Win rate: " + champion.winRate);
                    binding.winRate.setTextColor(Color.GREEN);
                }
                binding.position.setText(champion.position);
                binding.description.setText(champion.description);
            }
        });
    }
}