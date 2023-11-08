package com.example.masterdetailexercise;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.masterdetailexercise.databinding.FragmentNewChampionBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewChampion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewChampion extends Fragment {

    private FragmentNewChampionBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = FragmentNewChampionBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ChampionsViewModel championsViewModel = new ViewModelProvider(requireActivity()).get(ChampionsViewModel.class);
        NavController navController = Navigation.findNavController(view);


        binding.newChampionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String champName = binding.newChampName.getText().toString();
                String champRegion = binding.newChampRegion.getText().toString();
                String champPosition = binding.newChampPosition.getSelectedItem().toString();
                String champDescription = binding.newChampDesc.getText().toString();
                Float champWR = Float.parseFloat(binding.newChampWR.getText().toString());

                championsViewModel.add(new Champion(R.drawable.league_logo, champName, champPosition, champRegion, champWR, champDescription));

                navController.popBackStack();
            }
        });
    }
}