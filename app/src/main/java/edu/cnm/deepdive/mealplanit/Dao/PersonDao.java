package edu.cnm.deepdive.mealplanit.Dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.mealplanit.models.Person;

@Dao
public interface PersonDao {

    @Query("SELECT * FROM person")
    Person getAll();


    @Query("SELECT * FROM person WHERE first_name LIKE :firstName LIMIT 1")
    Person findFirstName(String firstName);


    @Query("SELECT * FROM person WHERE last_name LIKE :lastName LIMIT 1")
    Person findLastName(String lastName);


    @Query("SELECT * From person WHERE username LIKE :username LIMIT 1")
    Person findUsername(String username);


    @Query("SELECT * From person WHERE food_restrictions LIKE :foodRestrictions LIMIT 1")
    Person findFoodRestrictions(String foodRestrictions);

    @Insert
    long insert(Person person);
}
