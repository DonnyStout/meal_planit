package edu.cnm.deepdive.mealplanit.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.mealplanit.models.Food;

import java.util.List;

@Dao
public interface FoodDao {

    @Query("SELECT * FROM food LIMIT :start, :number")
    List<Food> getAll(int start, int number);

    @Query("SELECT * FROM food WHERE title LIKE :title LIMIT 5")
    List<Food> findByTitle(String title);

    @Query("SELECT * FROM food WHERE url LIKE :url LIMIT 5")
    List<Food> findByUrl(String url);

    @Insert
    long insert(Food food);

}