package eus.urko.roomAriketa;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ElementsRepository {

    Executor executor = Executors.newSingleThreadExecutor();
    ElementsDAO elementsDAO;

    ElementsRepository(Application application){
        elementsDAO = ElementsDB.obtainInstance(application).getElementsDAO();
    }

    //Methods to interact with room database
    LiveData<List<Element>> get(){
        return elementsDAO.getElements();
    }

    void insert(Element element){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                elementsDAO.insert(element);
            }
        });
    }

    void update(Element element, float rating){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                element.rating = rating;
                elementsDAO.update(element);
            }
        });
    }

    void delete(Element element){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                elementsDAO.delete(element);
            }
        });
    }

}
