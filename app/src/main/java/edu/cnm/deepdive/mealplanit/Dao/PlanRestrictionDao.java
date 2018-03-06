package edu.cnm.deepdive.mealplanit.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;
import edu.cnm.deepdive.mealplanit.models.PlanRestriction;

@Dao
public interface PlanRestrictionDao {

    @Update
    int update(PlanRestriction planRestriction);
}
