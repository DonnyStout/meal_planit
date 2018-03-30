package edu.cnm.deepdive.mealplanit.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;
import edu.cnm.deepdive.mealplanit.model.PlanRestriction;

/**
 * Data access model for {@link PlanRestriction} model.
 */
@Dao
public interface PlanRestrictionDao {

  /**
   * Inserts a new row into the PlanRestriction table.
   * @param planRestriction                         A Restriction.
   * @return                                        A long.
   */
  @Insert
  long insert(PlanRestriction planRestriction);

  /**
   * Updates a row in the PlanRestriction table.
   * @param planRestriction                        A Restriction
   * @return                                       An int.
   */
  @Update
  int update(PlanRestriction planRestriction);
}
