package edu.cnm.deepdive.mealplanit;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import edu.cnm.deepdive.mealplanit.Dao.*;
import edu.cnm.deepdive.mealplanit.models.*;

@Database(entities = {Food.class, Person.class, Browse.class}, version = 1)
public abstract class MealDatabase extends RoomDatabase {

    public abstract FoodDao foodDao();
    public abstract PersonDao personDao();
    public abstract BrowseDao browseDao();
}
