package edu.cnm.deepdive.mealplanit.service;

import edu.cnm.deepdive.mealplanit.servicemodel.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface BrowseService {


  @Headers("Accept: application/json")
  @GET("recipes/{id}/summary")
  Call<Recipe> list(@Header("X-Mashape-Key") String key, @Path("id") Long id);
}
