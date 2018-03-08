package edu.cnm.deepdive.mealplanit.Dao;


import android.arch.persistence.room.*;
import edu.cnm.deepdive.mealplanit.models.Restriction;

import java.util.List;

@Dao
public interface RestrictionDao {


    @Query("SELECT * FROM restriction")
    List<Restriction> getAll();

    @Query("SELECT * FROM restriction WHERE allergies LIKE :allergies LIMIT 1")
    Restriction findAllergies(String allergies);

    @Insert
    long insert(Restriction restriction);

    @Update
    int update(Restriction restriction);
}
