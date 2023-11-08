package unai.zz.interval.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import unai.zz.interval.dao.IntervalDAO;
import unai.zz.interval.dao.UserDAO;
import unai.zz.interval.model.Interval;
import unai.zz.interval.model.User;

@Database(entities = {User.class, Interval.class}, version = 1, exportSchema = false)
public abstract class DbAccess extends RoomDatabase {

    private static volatile DbAccess INSTANCE;

    public abstract UserDAO getUserDAO();
    public abstract IntervalDAO getIntervalDAO();

    public static DbAccess obtainInstance(final Context context){
        if (INSTANCE == null){
            synchronized ((DbAccess.class)){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context,
                            DbAccess.class, "interval.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
