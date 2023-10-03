package com.example.masterdetailexercise;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class ChampionsViewModel extends AndroidViewModel {

    ChampionsRepo championsRepo;
    MutableLiveData<List<Champion>> listChampionsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Champion> championSelected = new MutableLiveData<>();

    public ChampionsViewModel(@NonNull Application application){
        super(application);

        championsRepo = new ChampionsRepo();

        listChampionsMutableLiveData.setValue(championsRepo.get());
    }

    void select(Champion champion){ championSelected.setValue(champion);}

    MutableLiveData<Champion> selected(){ return championSelected;}

    MutableLiveData<List<Champion>> get(){ return listChampionsMutableLiveData;}

    void add(Champion champion){
        championsRepo.insert(champion, new ChampionsRepo.Callback() {
            @Override
            public void whenFinish(List<Champion> champions) {
                listChampionsMutableLiveData.setValue(champions);
            }
        });
    }

    void delete(Champion champion){
        championsRepo.delete(champion, new ChampionsRepo.Callback() {
            @Override
            public void whenFinish(List<Champion> champions) {
                listChampionsMutableLiveData.setValue(champions);
            }
        });
    }

}
