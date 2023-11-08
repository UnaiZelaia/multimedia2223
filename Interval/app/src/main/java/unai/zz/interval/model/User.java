package unai.zz.interval.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.checkerframework.common.aliasing.qual.Unique;

@Entity(tableName = "user",  indices = @Index(value = {"id"}, unique = true))
public class User {

    @PrimaryKey(autoGenerate = true)
    int id;
    @Unique
    String username;
    String password;
}
