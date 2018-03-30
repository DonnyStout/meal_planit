package edu.cnm.deepdive.mealplanit.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import edu.cnm.deepdive.mealplanit.model.Person;
import java.util.List;

/**
 * Data access object for the {@link Person} model.
 */
@Dao
public interface PersonDao {

  /**
   * Queries the database to list all of the people in the database.
   *
   * @return                           A Person.
   */
  @Query("SELECT * FROM person")
  List<Person> getAll();

  /**
   * Queries the database to find a person by their id.
   *
   * @param personId                    The id that a person has assigned to them.
   * @return                            A person.
   */
  @Query("SELECT * FROM person WHERE person_id LIKE :personId LIMIT 1")
  Person findByPersonId(Long personId);

  /**
   * Finds a user by their username.
   *
   * @param username                    A {@link String} of the persons username.
   * @return                            A Person.
   */
  @Query("SELECT * From person WHERE username LIKE :username LIMIT 1")
  Person findByUsername(String username);

  /**
   * Allows for the insertion into the Person table.
   *
   * @param person                      Inserts a person.
   * @return                            A {@link long}.
   */
  @Insert
  long insert(Person person);

  /**
   * Allows to update the Person table.
   *
   * @param person                      Updates a Person.
   * @return                            A {@link int}.
   */
  @Update
  int update(Person person);
}
