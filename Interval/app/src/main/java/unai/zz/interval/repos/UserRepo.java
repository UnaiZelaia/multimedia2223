package unai.zz.interval.repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import unai.zz.interval.dao.UserDAO;
import unai.zz.interval.db.DbAccess;
import unai.zz.interval.model.User;

public class UserRepo {

    UserDAO userDAO;
    Executor executor = Executors.newSingleThreadExecutor();

    public UserRepo(Application application){
        userDAO = DbAccess.obtainInstance(application).getUserDAO();
    }

    public LiveData<User> get(String user){ return userDAO.getUser(user); }

    public void insert(User user){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userDAO.insert(user);
            }
        });
    }

    public void delete(User user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userDAO.delete(user);
            }
        });
    }

    public void update(User user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userDAO.update(user);
            }
        });
    }
}
