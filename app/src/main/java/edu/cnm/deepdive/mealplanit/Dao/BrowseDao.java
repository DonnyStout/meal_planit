package edu.cnm.deepdive.mealplanit.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import edu.cnm.deepdive.mealplanit.models.Browse;

import java.util.Date;

@Dao
public interface BrowseDao {


    @Query("SELECT * FROM plans")
    Browse getAll();


    @Query("SELECT * FROM plans WHERE name LIKE :name LIMIT 1")
    Browse findName(String name);

    @Query("SELECT * FROM plans WHERE date LIKE :date LIMIT 1")
    Browse findDate(Date date);

    @Query("SELECT * FROM plans WHERE type LIKE :type LIMIT 1")
    Browse findType(String type);


    @Insert
    long insert(Browse browse);

}
