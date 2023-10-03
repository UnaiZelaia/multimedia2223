package com.example.masterdetailexercise;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.masterdetailexercise.databinding.FragmentRecyclerBinding;
import com.example.masterdetailexercise.databinding.FragmentViewHolderBinding;

import java.util.List;

public class Recycler extends Fragment {
    private FragmentRecyclerBinding binding;
    private ChampionsViewModel championsViewModel;
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

        championsViewModel = new ViewModelProvider(requireActivity()).get(ChampionsViewModel.class);
        navController = Navigation.findNavController(view);

        ChampionsAdapter championsAdapter = new ChampionsAdapter();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Champion champion = championsAdapter.getChampion(position);
                championsViewModel.delete(champion);
            }
        }).attachToRecyclerView(binding.recyclerView);

        binding.recyclerView.setAdapter(championsAdapter);
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        championsViewModel.get().observe(getViewLifecycleOwner(), new Observer<List<Champion>>() {
            @Override
            public void onChanged(List<Champion> elements) {
                championsAdapter.establishList(elements);
            }
        });

    }

    class ChampionViewHolder extends RecyclerView.ViewHolder{
        private final FragmentViewHolderBinding binding;

        public ChampionViewHolder(FragmentViewHolderBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class ChampionsAdapter extends RecyclerView.Adapter<ChampionViewHolder>{
        List<Champion> champions;

        @NonNull
        @Override
        public ChampionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ChampionViewHolder(FragmentViewHolderBinding.inflate(getLayoutInflater(), parent, false));
        }

        public Champion getChampion(int position){ return champions.get(position); }
        @Override
        public void onBindViewHolder(@NonNull ChampionViewHolder holder, int position) {
            Champion champion = champions.get(position);

            holder.binding.championName.setText(champion.name);
            holder.binding.championImage.setImageResource(champion.image);

            if (champion.winRate <= 50.0f){

                holder.binding.championWinRate.setText("Win rate: " + champion.winRate);
                holder.binding.championWinRate.setTextColor(Color.RED);
            }
            else {
                holder.binding.championWinRate.setText("Win rate: " + champion.winRate);
                holder.binding.championWinRate.setTextColor(Color.GREEN);
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    championsViewModel.select(champion);
                    navController.navigate(R.id.action_recycler_to_detail);
                }
            });
        }

        @Override
        public int getItemCount() { return champions != null ? champions.size() : 0;}

        public void establishList(List<Champion> champions){
            this.champions = champions;
            notifyDataSetChanged();
        }
    }
}