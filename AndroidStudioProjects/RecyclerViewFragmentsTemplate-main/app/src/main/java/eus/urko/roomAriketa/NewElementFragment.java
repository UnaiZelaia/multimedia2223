package eus.urko.roomAriketa;

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

import eus.urko.roomAriketa.databinding.FragmentNewElementBinding;

public class NewElementFragment extends Fragment {

    private FragmentNewElementBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentNewElementBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ElementsViewModel elementsViewModel = new ViewModelProvider(requireActivity()).get(ElementsViewModel.class);
        NavController navController = Navigation.findNavController(view);


        binding.newElementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.name.getText().toString();
                String description = binding.description.getText().toString();

                elementsViewModel.add(new Element(R.drawable.vuejs_original, name, description));

                navController.popBackStack();
            }
        });

    }
}