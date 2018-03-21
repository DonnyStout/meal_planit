package edu.cnm.deepdive.mealplanit.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import edu.cnm.deepdive.mealplanit.model.PersonRestriction;

@Dao
public interface PersonRestrictionDao {

  @Query("SELECT * FROM personrestriction WHERE person_id LIKE :personId LIMIT 1")
  PersonRestriction findByPersonId(long personId);

  @Query("SELECT * FROM personrestriction WHERE restriction_id LIKE :restrictionId LIMIT 1")
  PersonRestriction findByRestrictionId(long restrictionId);

  @Insert
  long insert(PersonRestriction personRestriction);

  @Update
  int update(PersonRestriction personRestriction);
}
