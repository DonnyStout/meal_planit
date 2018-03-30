package edu.cnm.deepdive.mealplanit.model;


import static android.arch.persistence.room.ForeignKey.CASCADE;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Model for creating the {@link Person} table in the database that allows accessors and mutators to access or change the
 * the information in the table. This model holds the information for all the users in the database.
 */
@Entity(indices = {@Index(value = {"username"}, unique = true)},
    foreignKeys = {@ForeignKey(entity = Diet.class,
        parentColumns = "diet_id",
        childColumns = "diet_id",
        onDelete = CASCADE)})

public class Person {


  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "person_id")
  private long personId;

  @NonNull
  @ColumnInfo(name = "first_name", collate = ColumnInfo.NOCASE)
  private String firstName;

  @NonNull
  @ColumnInfo(name = "last_name", collate = ColumnInfo.NOCASE)
  private String lastName;

  @NonNull
  @ColumnInfo(name = "username", collate = ColumnInfo.NOCASE)
  private String username;

  @ColumnInfo(name = "calories_per_day")
  private long caloriesPerDay;

  @ColumnInfo(name = "image_uri")
  private String imageUri;

  @ColumnInfo(name = "diet_id")
  private long dietId;

  public long getPersonId() {
    return personId;
  }

  public void setPersonId(long personId) {
    this.personId = personId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public long getCaloriesPerDay() {
    return caloriesPerDay;
  }

  public void setCaloriesPerDay(long caloriesPerDay) {
    this.caloriesPerDay = caloriesPerDay;
  }

  public long getDietId() {
    return dietId;
  }

  public String getImageUri() {
    return imageUri;
  }

  public void setImageUri(String imageUri) {
    this.imageUri = imageUri;
  }

  public void setDietId(long dietId) {
    this.dietId = dietId;
  }

  @Override
  public String toString() {
    return username;
  }

}
