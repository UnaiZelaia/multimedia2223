package unai.zz.interval.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import unai.zz.interval.model.User;
import unai.zz.interval.repos.UserRepo;

public class UserViewmodel extends AndroidViewModel {
    UserRepo userRepo;

    MutableLiveData<User> userSelected = new MutableLiveData<User>();

    public UserViewmodel(@NonNull Application application){
        super(application);
        userRepo = new UserRepo(application);
    }

    public LiveData<User> get(String user){ return userRepo.get(user); }


    void delete(User user) {
        userRepo.delete(user);
    }

    void update(User user) {
        userRepo.update(user);
    }

    void select(User user) {
        userSelected.setValue(user);
    }

    MutableLiveData<User> selected() {
        return userSelected;
    }
}
