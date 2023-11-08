package unai.zz.interval.repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import unai.zz.interval.dao.IntervalDAO;
import unai.zz.interval.db.DbAccess;
import unai.zz.interval.model.Interval;

public class IntervalRepo {

    IntervalDAO intervalDAO;
    Executor executor = Executors.newSingleThreadExecutor();

    public IntervalRepo(Application application){
        intervalDAO = DbAccess.obtainInstance(application).getIntervalDAO();
    }

    public LiveData<List<Interval>> get(int id){ return intervalDAO.getUserIntervals(id); }

    public void insert(Interval interval) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                intervalDAO.insert(interval);
            }
        });
    }

    public void delete(Interval interval) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                intervalDAO.delete(interval);
            }
        });
    }

    public void update(Interval interval) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                intervalDAO.update(interval);
            }
        });
    }
}
