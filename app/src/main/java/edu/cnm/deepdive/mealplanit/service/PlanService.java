package edu.cnm.deepdive.mealplanit.service;

import edu.cnm.deepdive.mealplanit.model.Plan;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface PlanService {

  @Headers("Accept: application/json")
  @GET("/generate?diet={dietType}&exclude={allergies}&targetCalories={dailyCalories}&timeFrame=day")
  Call<List<Plan>> list(@Path("dietType") String dietType, @Path("allergies") String allergies,
      @Path("dailyCalories") Integer dailyCalories);


}
