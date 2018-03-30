package edu.cnm.deepdive.mealplanit.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import edu.cnm.deepdive.mealplanit.model.PersonRestriction;

/**
 * Data access object for {@link PersonRestriction}.
 */
@Dao
public interface PersonRestrictionDao {


  /**
   * Finds the personrestriction row by the persons id.
   * @param personId                            A {@link long} that identifies the user.
   * @return                                    A PersonRestriction.
   */
  @Query("SELECT * FROM personrestriction WHERE person_id LIKE :personId LIMIT 1")
  PersonRestriction findByPersonId(long personId);

  /**
   * Allows for the ability to insert a row into the personrestriction table.
   * @param personRestriction                     Inserts a new personrestriction row.
   * @return                                      A {@link long}.
   */
  @Insert
  long insert(PersonRestriction personRestriction);

  /**
   * Allows for the ability to update a row into the personrestriction table.
   * @param personRestriction                      Updates the row in the personrestriction table.
   * @return                                       An {@link int}
   */
  @Update
  int update(PersonRestriction personRestriction);
}
