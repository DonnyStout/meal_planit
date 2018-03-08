package edu.cnm.deepdive.mealplanit.dao;

import android.arch.persistence.room.*;
import edu.cnm.deepdive.mealplanit.models.Plan;

import java.util.Date;

@Dao
public interface PlanDao {


    @Query("SELECT * FROM plan")
    Plan getAll();

    @Query("SELECT * FROM plan WHERE date LIKE :date LIMIT 1")
    Plan findDate(Date date);

    @Query("SELECT * FROM plan WHERE breakfast_id LIKE :breakfastId LIMIT 1")
    Plan findBreakfastId(long breakfastId);

    @Query("SELECT * FROM plan WHERE breakfast_title LIKE :breakfastTitle LIMIT 1")
    Plan findBreakfastTitle(String breakfastTitle);

    @Query("SELECT * FROM plan WHERE breakfast_url LIKE :breakfastUrl LIMIT 1")
    Plan findBreakfastUrl(String breakfastUrl);

    @Query("SELECT * FROM plan WHERE lunch_id LIKE :lunchId LIMIT 1")
    Plan findLunchId(long lunchId);

    @Query("SELECT * FROM plan WHERE lunch_title LIKE :lunchTitle LIMIT 1")
    Plan findLunchTitle(String lunchTitle);

    @Query("SELECT * FROM plan WHERE lunch_url LIKE :lunchUrl LIMIT 1")
    Plan findLunchUrl(String lunchUrl);

    @Query("SELECT * FROM plan WHERE dinner_id LIKE :dinnerId LIMIT 1")
    Plan findDinnerId(long dinnerId);

    @Query("SELECT * FROM plan WHERE dinner_title LIKE :dinnerTitle LIMIT 1")
    Plan findDinnerTitle(String dinnerTitle);

    @Query("SELECT * FROM plan WHERE dinner_url LIKE :dinnerUrl LIMIT 1")
    Plan findDinnerUrl(String dinnerUrl);


    @Insert
    long insert(Plan plan);

    @Delete
    int delete(Plan plan);
}
