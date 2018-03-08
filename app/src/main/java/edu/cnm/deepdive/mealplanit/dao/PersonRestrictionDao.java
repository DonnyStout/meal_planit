package edu.cnm.deepdive.mealplanit.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import edu.cnm.deepdive.mealplanit.models.PersonRestriction;

@Dao
public interface PersonRestrictionDao {

    @Query("SELECT * FROM personrestriction WHERE person_id LIKE :personId LIMIT 1")
    PersonRestriction findPersonId(long personId);

    @Insert
    long insert(PersonRestriction personRestriction);

    @Update
    int update(PersonRestriction personRestriction);
}
