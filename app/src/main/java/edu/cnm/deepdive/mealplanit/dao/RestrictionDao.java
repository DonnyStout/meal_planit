package edu.cnm.deepdive.mealplanit.dao;


import android.arch.persistence.room.*;
import edu.cnm.deepdive.mealplanit.model.Restriction;

import java.util.List;

/**
 * Data access object for the {@link Restriction} model.
 */
@Dao
public interface RestrictionDao {


  /**
   * Query that lists out all the rows in restriction.
   * @return                                  A {@link List} of Restriction
   */
  @Query("SELECT * FROM restriction")
  List<Restriction> getAll();

  /**
   * Finds the row in the table according to the restriction id.
   * @param restrictionId                     The identifier for the restriction.
   * @return                                  A Restriction.
   */
  @Query("SELECT * FROM restriction WHERE restriction_id LIKE :restrictionId LIMIT 1")
  Restriction findByRestrictionId(long restrictionId);

  /**
   * Inserts a new row into the Restriction table.
   * @param restriction                       A Restriction.
   * @return                                  A long.
   */
  @Insert
  long insert(Restriction restriction);

  /**
   * Updates a row in the restriction table.
   * @param restriction                      A Restriction.
   * @return                                 An int.
   */
  @Update
  int update(Restriction restriction);
}
