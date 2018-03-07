package edu.cnm.deepdive.mealplanit.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.mealplanit.models.Restriction;

@Dao
public interface RestrictionDao {

    @Query("SELECT * FROM restriction WHERE allergies LIKE :allergies LIMIT 1")
    Restriction findAllergies(String allergies);

    @Insert
    long insert(Restriction restriction);

    @Delete
    int delete(Restriction restriction);
}
