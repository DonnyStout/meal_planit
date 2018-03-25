package edu.cnm.deepdive.mealplanit.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {@ForeignKey(entity = Person.class,
    parentColumns = "person_id",
    childColumns = "person_id",
    onDelete = CASCADE),
    @ForeignKey(entity = Diet.class,
        parentColumns = "diet_id",
        childColumns = "diet_id",
        onDelete = CASCADE)})
public class Plan {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "plan_id")
  private long planId;

  @NonNull
  @ColumnInfo(name = "date")
  private String date;

  @ColumnInfo(name = "breakfast_id")
  private Long breakfastId;

  @ColumnInfo(name = "breakfast_title")
  private String breakfastTitle;

  @ColumnInfo(name = "breakfast_url")
  private String breakfastUrl;

  @ColumnInfo(name = "lunch_id")
  private Long lunchId;

  @ColumnInfo(name = "lunch_title")
  private String lunchTitle;

  @ColumnInfo(name = "lunch_url")
  private String lunchUrl;

  @ColumnInfo(name = "dinner_id")
  private Long dinnerId;

  @ColumnInfo(name = "dinner_title")
  private String dinnerTitle;

  @ColumnInfo(name = "dinner_url")
  private String dinnerUrl;

  @ColumnInfo(name = "person_id", index = true)
  private long personId;

  @ColumnInfo(name = "diet_id", index = true)
  private Long dietId;

  public long getPlanId() {
    return planId;
  }

  public void setPlanId(long planId) {
    this.planId = planId;
  }

  @NonNull
  public String getDate() {
    return date;
  }

  public void setDate(@NonNull String date) {
    this.date = date;
  }

  public Long getBreakfastId() {
    return breakfastId;
  }

  public void setBreakfastId(Long breakfastId) {
    this.breakfastId = breakfastId;
  }

  public String getBreakfastTitle() {
    return breakfastTitle;
  }

  public void setBreakfastTitle(String breakfastTitle) {
    this.breakfastTitle = breakfastTitle;
  }

  public String getBreakfastUrl() {
    return breakfastUrl;
  }

  public void setBreakfastUrl(String breakfastUrl) {
    this.breakfastUrl = breakfastUrl;
  }

  public Long getLunchId() {
    return lunchId;
  }

  public void setLunchId(Long lunchId) {
    this.lunchId = lunchId;
  }

  public String getLunchTitle() {
    return lunchTitle;
  }

  public void setLunchTitle(String lunchTitle) {
    this.lunchTitle = lunchTitle;
  }

  public String getLunchUrl() {
    return lunchUrl;
  }

  public void setLunchUrl(String lunchUrl) {
    this.lunchUrl = lunchUrl;
  }

  public Long getDinnerId() {
    return dinnerId;
  }

  public void setDinnerId(Long dinnerId) {
    this.dinnerId = dinnerId;
  }

  public String getDinnerTitle() {
    return dinnerTitle;
  }

  public void setDinnerTitle(String dinnerTitle) {
    this.dinnerTitle = dinnerTitle;
  }

  public String getDinnerUrl() {
    return dinnerUrl;
  }

  public void setDinnerUrl(String dinnerUrl) {
    this.dinnerUrl = dinnerUrl;
  }

  public long getPersonId() {
    return personId;
  }

  public void setPersonId(long personId) {
    this.personId = personId;
  }

  public Long getDietId() {
    return dietId;
  }

  public void setDietId(Long dietId) {
    this.dietId = dietId;
  }

  @Override
  public String toString() {
    return planId + " " + date + " " + breakfastTitle + " " + lunchTitle + " " + dinnerTitle + " "
        + personId;
  }
}
