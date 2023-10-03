package com.example.recyclerariketa;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class ElementsViewModel extends AndroidViewModel {

    ElementsRepo elementsRepository;
    MutableLiveData<List<Element>> listElementsMutableLiveData = new MutableLiveData<>();

    public ElementsViewModel(@NonNull Application application) {
        super(application);

        elementsRepository = new ElementsRepo();

        listElementsMutableLiveData.setValue(elementsRepository.get());
    }


    MutableLiveData<List<Element>> get(){
        return listElementsMutableLiveData;
    }

    void add(Element element){
        elementsRepository.insert(element, new ElementsRepo.Callback() {
            public void whenFinish(List<Element> elements) {
                listElementsMutableLiveData.setValue(elements);
            }
        });
    }

    void delete(Element element){
        elementsRepository.delete(element, new ElementsRepo.Callback() {
            @Override
            public void whenFinish(List<Element> elements) {
                listElementsMutableLiveData.setValue(elements);
            }
        });
    }

    void update(Element element, float rating){
        elementsRepository.update(element, rating, new ElementsRepo.Callback() {
            @Override
            public void whenFinish(List<Element> elements) {
                listElementsMutableLiveData.setValue(elements);
            }
        });
    }
}
