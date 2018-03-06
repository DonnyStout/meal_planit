package edu.cnm.deepdive.mealplanit.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.mealplanit.models.Diet;

@Dao
public interface DietDao {

    @Query("SELECT * FROM diet WHERE diet_type LIKE :dietType LIMIT 1")
    Diet findDietType(String dietType);

    @Insert
    long insert(Diet diet);

    @Delete
    int delete(Diet diet);
}
