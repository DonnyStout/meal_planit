package edu.cnm.deepdive.mealplanit.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.mealplanit.models.Restriction;

@Dao
public interface RestrictionDao {

    @Query("SELECT * FROM restriction WHERE excluded_ingredients LIKE :excludedIngredients LIMIT 1")
    Restriction findExcludedIngredients(String excludedIngredients);

    @Insert
    long insert(Restriction restriction);

    @Delete
    int delete(Restriction restriction);
}
