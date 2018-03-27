package edu.cnm.deepdive.mealplanit.servicemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipe {

  @SerializedName("id")
  @Expose
  private Integer id;

  @SerializedName("title")
  @Expose
  private String title;

  @SerializedName("summary")
  @Expose
  private String summary;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

}
