package edu.cnm.deepdive.mealplanit.dao;


import android.arch.persistence.room.*;
import edu.cnm.deepdive.mealplanit.models.Restriction;

import java.util.List;

@Dao
public interface RestrictionDao {


    @Query("SELECT * FROM restriction")
    List<Restriction> getAll();

    @Query("SELECT * FROM restriction WHERE allergy LIKE :allergy LIMIT 1")
    Restriction findAllergy(String allergy);

    @Insert
    long insert(Restriction restriction);

    @Update
    int update(Restriction restriction);
}
