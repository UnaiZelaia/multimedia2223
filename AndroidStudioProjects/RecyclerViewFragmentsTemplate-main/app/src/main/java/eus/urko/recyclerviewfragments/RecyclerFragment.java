package eus.urko.recyclerviewfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import eus.urko.recyclerviewfragments.databinding.FragmentRecyclerBinding;
import eus.urko.recyclerviewfragments.databinding.FragmentViewHolderBinding;

public class RecyclerFragment extends Fragment {

    private FragmentRecyclerBinding binding;
    private ElementsViewModel elementsViewModel;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentRecyclerBinding.inflate(inflater, container, false)).getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        elementsViewModel = new ViewModelProvider(requireActivity()).get(ElementsViewModel.class);
        navController = Navigation.findNavController(view);

        ElementsAdapter elementsAdapter = new ElementsAdapter();



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Element element = elementsAdapter.getElement(position);
                elementsViewModel.delete(element);
            }
        }).attachToRecyclerView(binding.recyclerView);


        binding.recyclerView.setAdapter(elementsAdapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        elementsViewModel.get().observe(getViewLifecycleOwner(), new Observer<List<Element>>() {
            @Override
            public void onChanged(List<Element> elements) {
                elementsAdapter.establishList(elements);
            }
        });


        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_recyclerFragment_to_newElementFragment);
            }
        });
    }













    class ElementViewHolder extends RecyclerView.ViewHolder{
        private final FragmentViewHolderBinding binding;

        public ElementViewHolder(FragmentViewHolderBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    class ElementsAdapter extends RecyclerView.Adapter<ElementViewHolder>{
        List <Element> elements;

        @NonNull
        @Override
        public ElementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ElementViewHolder(FragmentViewHolderBinding.inflate(getLayoutInflater(), parent, false));

        }

        public Element getElement(int position){
            return elements.get(position);
        }

        @Override
        public void onBindViewHolder(@NonNull ElementViewHolder holder, int position) {
            Element element = elements.get(position);

            holder.binding.elementName.setText(element.name);
            holder.binding.elementImage.setImageResource(element.image);
            holder.binding.elementRating.setRating(element.rating);
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    elementsViewModel.select(element);
                    navController.navigate(R.id.action_recyclerFragment_to_detailFragment);
                }
            });

            holder.binding.elementRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if(fromUser){
                        elementsViewModel.update(element, rating);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return elements != null ? elements.size() : 0;
        }

        public void establishList(List<Element> elements){
            this.elements = elements;
            notifyDataSetChanged();
        }
    }
}