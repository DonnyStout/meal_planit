package edu.cnm.deepdive.mealplanit.service;

import edu.cnm.deepdive.mealplanit.servicemodel.MealList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PlanService {

  @Headers("Accept: application/json")
  @GET("generate?")
  Call<MealList> list(@Header("X-Mashape-Key") String key, @Query("diet") String dietType, @Query("exclude") String allergies,
      @Query("targetCalories") Long dailyCalories, @Query("timeFrame") String time);


}
