package edu.cnm.deepdive.mealplanit.dao;


import android.arch.persistence.room.*;
import edu.cnm.deepdive.mealplanit.model.Diet;
import java.util.List;

/**
 * The data access object for the {@link Diet} model.
 */
@Dao
public interface DietDao {


  /**
   * Query that gets a list of rows in Diet.
   *
   * @return                          List of Diet objects.
   */
  @Query("SELECT * FROM diet")
  List<Diet> getAll();


  /**
   * Query that return a Diet based on the diet id.
   *
   * @param dietId                      A {@link long} id that specifies the diet..
   * @return                            A Diet.
   */
  @Query("SELECT * FROM diet WHERE diet_id LIKE :dietId LIMIT 1")
  Diet findByDietId(long dietId);

  /**
   * Allows for the insertion into the Diet table.
   *
   * @param diet                         Inserts a Diet.
   * @return                             A {@link long}
   */
  @Insert
  long insert(Diet diet);

  /**
   * Allows for the ability to update the Diet table.
   *
   * @param diet                         Updates the Diet table
   * @return                             An {@link int}
   */
  @Update
  int update(Diet diet);
}
