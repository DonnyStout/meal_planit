package edu.cnm.deepdive.mealplanit.dao;


import android.arch.persistence.room.*;
import edu.cnm.deepdive.mealplanit.model.Diet;

import java.util.List;

/**
 * The data access object for the <code>Diet</code> model.
 */
@Dao
public interface DietDao {

  // Queries for a list of the diets that are available in the database
  @Query("SELECT * FROM diet")
  List<Diet> getAll();

  // Query for a specific diet in the database
  @Query("SELECT * FROM diet WHERE diet_type LIKE :dietType LIMIT 1")
  Diet findDietType(String dietType);

  @Query("SELECT * FROM diet WHERE diet_id LIKE :dietId LIMIT 1")
  Diet findByDietId(long dietId);

  // Insert object for the Diet model
  @Insert
  long insert(Diet diet);

  // Update object for the diet model
  @Update
  int update(Diet diet);
}
