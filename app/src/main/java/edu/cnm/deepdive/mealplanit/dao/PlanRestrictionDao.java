package edu.cnm.deepdive.mealplanit.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;
import edu.cnm.deepdive.mealplanit.models.PlanRestriction;

@Dao
public interface PlanRestrictionDao {

    @Insert
    long insert(PlanRestriction planRestriction);

    @Update
    int update(PlanRestriction planRestriction);
}
