package eus.urko.roomAriketa;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "element")
public class Element {
        @PrimaryKey(autoGenerate = true)
                int id;

        String name;
        String description;
        float rating;
        int image;

        public Element(int image, String name, String description) {
            this.name = name;
            this.description = description;
            this.image=image;
    }
}
