package unai.zz.interval.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "interval",  indices = @Index(value = {"id"}, unique = true), foreignKeys = @ForeignKey(entity = User.class,
                                                                                                            parentColumns = "id",
                                                                                                            childColumns = "user_id"))
public class Interval {
    @PrimaryKey(autoGenerate = true)
    int id;
    int id_user;
    String interval;
}
