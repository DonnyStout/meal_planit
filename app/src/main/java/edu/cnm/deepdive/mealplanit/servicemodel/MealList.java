package edu.cnm.deepdive.mealplanit.servicemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Handles that response from the API with {@link retrofit2.Retrofit} as a list of meals.
 */
public class MealList {

  @SerializedName("meals")
  @Expose
  private List<Meal> meals = null;


  public List<Meal> getMeals() {
    return meals;
  }


  /**
   * The meals that are listed in the <code>MealList</code> parent class and handles the response received.
   */
  public class Meal {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("image")
    @Expose
    private String image;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }


    public String getImage() {
      return image;
    }

    public void setImage(String image) {
      this.image = image;
    }

  }
}