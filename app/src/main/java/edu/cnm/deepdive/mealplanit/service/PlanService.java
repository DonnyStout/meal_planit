package edu.cnm.deepdive.mealplanit.service;

import edu.cnm.deepdive.mealplanit.servicemodel.MealList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface PlanService {

  @Headers("Accept: application/json")
  @GET("/generate?diet={dietType}&exclude={allergies}&targetCalories={dailyCalories}&timeFrame=day")
  Call<MealList> list(@Path("dietType") String dietType, @Path("allergies") String allergies,
      @Path("dailyCalories") Long dailyCalories);


}
