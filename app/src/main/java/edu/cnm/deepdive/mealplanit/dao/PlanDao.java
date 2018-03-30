package edu.cnm.deepdive.mealplanit.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import edu.cnm.deepdive.mealplanit.model.Plan;
import org.joda.time.LocalDate;

/**
 * Data access object for the {@link Plan} model.
 */
@Dao
public interface PlanDao {

  /**
   * Query that finds the row in the table by a date using {@link LocalDate} and <code>Person</code> id.
   * @param date                              The date that has been allocated to that plan.
   * @param personId                          The identifier for which person the plan belongs to.
   * @return                                  A Plan.
   */
  @Query("SELECT * FROM plan WHERE date like :date AND person_id LIKE :personId LIMIT 1")
  Plan findByDateAndPersonId(LocalDate date, long personId);

  /**
   * Allows for the insertion of new plans rows into the table.
   * @param plan                              An insertion of the plan table.
   * @return                                  A {@link long}.
   */
  @Insert
  long insert(Plan plan);

  /**
   * Updates the row in the plan table.
   * @param plan                              A plan table to be updated.
   * @return                                  A {@link int}
   */
  @Update
  int update(Plan plan);
}
