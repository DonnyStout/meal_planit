package edu.cnm.deepdive.mealplanit.servicemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MealList {

  @SerializedName("meals")
  @Expose
  private List<Meal> meals = null;


  public List<Meal> getMeals() {
    return meals;
  }

  public void setMeals(List<Meal> meals) {
    this.meals = meals;
  }


  public class Meal {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("readyInMinutes")
    @Expose
    private Integer readyInMinutes;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("imageUrls")
    @Expose
    private List<String> imageUrls = null;

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

    public Integer getReadyInMinutes() {
      return readyInMinutes;
    }

    public void setReadyInMinutes(Integer readyInMinutes) {
      this.readyInMinutes = readyInMinutes;
    }

    public String getImage() {
      return image;
    }

    public void setImage(String image) {
      this.image = image;
    }

    public List<String> getImageUrls() {
      return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
      this.imageUrls = imageUrls;
    }

  }
}