package unai.zz.interval.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import unai.zz.interval.model.Interval;
import unai.zz.interval.repos.IntervalRepo;

public class IntervalViewmodel extends AndroidViewModel {
    IntervalRepo intervalRepo;

    MutableLiveData<Interval> intervalSelected = new MutableLiveData<>();

    public IntervalViewmodel(@NonNull Application application){
        super(application);
        intervalRepo = new IntervalRepo(application);
    }

    public LiveData<List<Interval>> get(int id){ return intervalRepo.get(id); }
}
