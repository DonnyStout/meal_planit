package edu.cnm.deepdive.mealplanit.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Food {


    @PrimaryKey(autoGenerate = false)
    private long foodId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "ready_in_minutes")
    private int readyInMinutes;

    @ColumnInfo(name = "url")
    private String url;
    // https://spoonacular.com/recipeImages/
    // https://spoonacular.com/recipeImages/{ID}-{SIZE}.{TYPE}
    //
    //    90x90
    //    240x150
    //    312x150
    //    312x231
    //    480x360
    //    556x370
    //    636x393


    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return title + " " + readyInMinutes + " " + url;
    }
}
