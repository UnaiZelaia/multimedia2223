package eus.urko.recyclerviewfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import eus.urko.recyclerviewfragments.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentDetailBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ElementsViewModel elementsViewModel = new ViewModelProvider(requireActivity()).get(ElementsViewModel.class);


        elementsViewModel.selected().observe(getViewLifecycleOwner(), new Observer<Element>() {
            @Override
            public void onChanged(Element element) {
                binding.name.setText(element.name);
                binding.description.setText(element.description);
                binding.rating.setRating(element.rating);

                binding.rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        if(fromUser){
                            elementsViewModel.update(element, rating);
                        }
                    }
                });
            }
        });

    }
}