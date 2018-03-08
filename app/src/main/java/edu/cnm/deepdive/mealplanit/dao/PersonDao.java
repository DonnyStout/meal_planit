package edu.cnm.deepdive.mealplanit.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import edu.cnm.deepdive.mealplanit.models.Person;

import java.util.List;

@Dao
public interface PersonDao {


    @Query("SELECT * FROM person")
    List<Person> getAll();

    @Query("SELECT * FROM person WHERE first_name LIKE :firstName LIMIT 1")
    Person findFirstName(String firstName);


    @Query("SELECT * FROM person WHERE last_name LIKE :lastName LIMIT 1")
    Person findLastName(String lastName);


    @Query("SELECT * From person WHERE username LIKE :username LIMIT 1")
    Person findUsername(String username);


    @Query("SELECT * From person WHERE calories_per_day LIKE :caloriesPerDay LIMIT 1")
    Person findCaloriesPerDay(long caloriesPerDay);

    @Insert
    long insert(Person person);

    @Update
    int update(Person person);
}
