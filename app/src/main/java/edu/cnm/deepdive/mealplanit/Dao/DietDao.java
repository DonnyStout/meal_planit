package edu.cnm.deepdive.mealplanit.Dao;


import android.arch.persistence.room.*;
import edu.cnm.deepdive.mealplanit.models.Diet;

import java.util.List;

@Dao
public interface DietDao {


    @Query("SELECT * FROM diet")
    List<Diet> getAll();

    @Query("SELECT * FROM diet WHERE diet_type LIKE :dietType LIMIT 1")
    Diet findDietType(String dietType);

    @Insert
    long insert(Diet diet);

    @Update
    int update(Diet diet);
}
