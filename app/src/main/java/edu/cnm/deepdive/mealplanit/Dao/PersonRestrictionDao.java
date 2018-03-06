package edu.cnm.deepdive.mealplanit.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import edu.cnm.deepdive.mealplanit.models.PersonRestriction;

@Dao
public interface PersonRestrictionDao {

    @Update
    int update(PersonRestriction personRestriction);
}
